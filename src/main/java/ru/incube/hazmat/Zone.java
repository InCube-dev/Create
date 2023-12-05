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
        if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() == Registry.HELMET_ONE_HAZMAT &&
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Registry.CHESTPLATE_ONE_HAZMAT &&
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == Registry.LEGGINGS_ONE_HAZMAT &&
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == Registry.BOOTS_ONE_HAZMAT) {
            applyEffects(player, new StatusEffectInstance(StatusEffects.NAUSEA, 10, 10), new StatusEffectInstance(StatusEffects.BLINDNESS, 10, 10), new StatusEffectInstance(StatusEffects.POISON, 10, 1));
        } else if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() == Registry.HELMET_TWO_HAZMAT &&
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Registry.CHESTPLATE_TWO_HAZMAT &&
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == Registry.LEGGINGS_TWO_HAZMAT &&
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == Registry.BOOTS_TWO_HAZMAT) {
            applyEffects(player, new StatusEffectInstance(StatusEffects.NAUSEA, 10, 4), new StatusEffectInstance(StatusEffects.BLINDNESS, 10));
        } else if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() == Registry.HELMET_TREE_HAZMAT &&
                player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Registry.CHESTPLATE_TREE_HAZMAT &&
                player.getEquippedStack(EquipmentSlot.LEGS).getItem() == Registry.LEGGINGS_TREE_HAZMAT &&
                player.getEquippedStack(EquipmentSlot.FEET).getItem() == Registry.BOOTS_TREE_HAZMAT) {
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