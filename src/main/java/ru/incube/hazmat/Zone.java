package ru.incube.hazmat;

import lombok.extern.slf4j.Slf4j;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

@Slf4j(topic = "Zone")
public class Zone {
    private final int x;
    private final int z;
    private final int r;

    public Zone(int x, int z, int r) {
        this.x = x;
        this.z = z;
        this.r = r + 1;
        ServerTickEvents.START_WORLD_TICK.register(this::onTick);
    }

    private void onTick(ServerWorld serverWorld) {
        BlockPos pos1 = new BlockPos(x + r, serverWorld.getTopY(), z + r);
        BlockPos pos2 = new BlockPos(x - r, serverWorld.getBottomY(), z - r);
        List<Entity> entities = serverWorld.getOtherEntities(null, new Box(pos1, pos2));

        for (Entity entity : entities) {
            if (entity.isPlayer()) {
                PlayerEntity player = (PlayerEntity) entity;
                applyEffectsBasedOnArmor(player);
            }
        }
    }

    private void applyEffectsBasedOnArmor(PlayerEntity player) {
        if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() == Registry.LEATHER_HELMET_HEAD &&
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Registry.LEATHER_CHESTPLATE_CHEST &&
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == Registry.LEATHER_LEGGINGS_LEGS &&
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == Registry.LEATHER_BOOTS_FEET) {
            applyEffects(player, new StatusEffectInstance(StatusEffects.NAUSEA, 10, 10), new StatusEffectInstance(StatusEffects.BLINDNESS, 10, 10), new StatusEffectInstance(StatusEffects.POISON, 10, 1));
        } else if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() == Registry.IRON_HELMET_HEAD &&
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Registry.IRON_CHESTPLATE_CHEST &&
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == Registry.IRON_LEGGINGS_LEGS &&
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == Registry.IRON_BOOTS_FEET) {
            applyEffects(player, new StatusEffectInstance(StatusEffects.NAUSEA, 10, 4), new StatusEffectInstance(StatusEffects.BLINDNESS, 10));
        } else if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() == Registry.DIAMOND_HELMET_HEAD &&
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Registry.DIAMOND_CHESTPLATE_CHEST &&
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == Registry.DIAMOND_LEGGINGS_LEGS &&
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == Registry.DIAMOND_BOOTS_FEET) {
            applyEffects(player, new StatusEffectInstance(StatusEffects.BLINDNESS, 10));
        } else {
            applyEffects(player, new StatusEffectInstance(StatusEffects.NAUSEA, 10, 10), new StatusEffectInstance(StatusEffects.WITHER, 10, 1), new StatusEffectInstance(StatusEffects.BLINDNESS, 10, 10), new StatusEffectInstance(StatusEffects.LEVITATION, 10, 10), new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 10, 1));
        }
    }

    private void applyEffects(PlayerEntity player, StatusEffectInstance... effects) {
        for (StatusEffectInstance effect : effects) {
            player.addStatusEffect(effect);
        }
    }
}