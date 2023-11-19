package ru.vkprofi.hazmat;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class zone {
    private static final Logger log = LogManager.getLogger("[Hazmat] (zone)");
    private int x;private int z;private int r;
    public zone(int x,int z,int r){
        this.x=x;this.z=z;this.r=r+1;
        ServerTickEvents.START_WORLD_TICK.register(this::onTick);
    }
    private void onTick(ServerWorld serverWorld) {
        BlockPos pos1 = new BlockPos(x+r,serverWorld.getTopY(),z+r);
        BlockPos pos2 = new BlockPos(x-r,serverWorld.getBottomY(),z-r);
        List<Entity> entities = serverWorld.getOtherEntities(null,new Box(pos1,pos2));
        for(Entity entity : entities){
            if(entity.isPlayer()){
                PlayerEntity player = serverWorld.getClosestPlayer(entity,30000000);

                // Хазмат 1го уровня
                if(
                        player.getEquippedStack(EquipmentSlot.HEAD).toString().equals("1 "+registry.HELMET_ONE_HAZMAT.toString()) &&
                        player.getEquippedStack(EquipmentSlot.CHEST).toString().equals("1 "+registry.CHESTPLATE_ONE_HAZMAT.toString()) &&
                        player.getEquippedStack(EquipmentSlot.LEGS).toString().equals("1 "+registry.LEGGINGS_ONE_HAZMAT.toString()) &&
                        player.getEquippedStack(EquipmentSlot.FEET).toString().equals("1 "+registry.BOOTS_ONE_HAZMAT.toString())
                ){
                    /*
                     * Тошнота
                     * Слепота
                     * Левитация
                     * Моментальный урон
                     */
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 10,10));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 10,10));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 10,1));

                // Хазмат 2го уровня
                } else if(
                        player.getEquippedStack(EquipmentSlot.HEAD).toString().equals("1 "+registry.HELMET_TWO_HAZMAT.toString()) &&
                        player.getEquippedStack(EquipmentSlot.CHEST).toString().equals("1 "+registry.CHESTPLATE_TWO_HAZMAT.toString()) &&
                        player.getEquippedStack(EquipmentSlot.LEGS).toString().equals("1 "+registry.LEGGINGS_TWO_HAZMAT.toString()) &&
                        player.getEquippedStack(EquipmentSlot.FEET).toString().equals("1 "+registry.BOOTS_TWO_HAZMAT.toString())
                ){
                    /*
                     * Тошнота
                     * Слепота
                     * Левитация
                     */
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 10,4));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 10));

                // Хазмат 3го уровня
                } else if(
                        player.getEquippedStack(EquipmentSlot.HEAD).toString().equals("1 "+registry.HELMET_TREE_HAZMAT.toString()) &&
                        player.getEquippedStack(EquipmentSlot.CHEST).toString().equals("1 "+registry.CHESTPLATE_TREE_HAZMAT.toString()) &&
                        player.getEquippedStack(EquipmentSlot.LEGS).toString().equals("1 "+registry.LEGGINGS_TREE_HAZMAT.toString()) &&
                        player.getEquippedStack(EquipmentSlot.FEET).toString().equals("1 "+registry.BOOTS_TREE_HAZMAT.toString())
                ){
                    /*
                     * Ослепление
                     */
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 10));
                // Если нет хазмата
                } else {
                    /*
                     * Тошнота
                     * Иссушение
                     * Слепота
                     * Левитация
                     * Моментальный урон
                     */
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 10,10));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 10,1));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 10,10));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 10,10));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 10,1));
                }
            }
        }
    }
}
