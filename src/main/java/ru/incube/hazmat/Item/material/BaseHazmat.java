package ru.incube.hazmat.Item.material;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

/**
 * Базовый класс для кастомной брони
 */
public abstract class BaseHazmat implements ArmorMaterial {
    private final int[] BASE_DURABILITY = new int[]{55, 80, 75, 65};
    private final int[] PROTECT_VALUE = new int[]{1, 3, 2, 1};

    /**
     * Получить прочность слота
     *
     * @param slot слот
     */
    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()];
    }

    /**
     * Получить количество защиты слота
     *
     * @param slot слот
     */
    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECT_VALUE[slot.getEntitySlotId()];
    }

    /**
     * Получить зачаровываемость
     */
    @Override
    public int getEnchantability() {
        return 15;
    }

    /**
     * Получить звук экипировки
     *
     * @return звук экипировки
     */
    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    /**
     * Получить ингредиент для ремонта
     *
     * @return ингредиент кожи
     */
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.LEATHER);
    }

    /**
     * Получить жесткость
     */
    @Override
    public float getToughness() {
        return 0;
    }

    /**
     * Получить сопротивление отбрасыванию
     */
    @Override
    public float getKnockbackResistance() {
        return 1;
    }

    /**
     * Получить эффекты
     *
     * @return эффекты
     */
    public abstract StatusEffect[] getEffects();
}