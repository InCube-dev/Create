package ru.incube.hazmat.item.material;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;

public class DiamondHazmat extends BaseHazmat {
    public final StatusEffect[] effects = new StatusEffect[]{StatusEffects.WITHER};

    @Override
    public String getName() {
        return "hazmattree";
    }

    @Override
    public float getToughness() {
        return 10;
    }

    @Override
    public StatusEffect[] getEffects() {
        return effects;
    }
}