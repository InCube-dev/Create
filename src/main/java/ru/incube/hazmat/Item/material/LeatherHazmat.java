package ru.incube.hazmat.Item.material;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;

public class LeatherHazmat extends BaseHazmat {
    public final StatusEffect[] effects = new StatusEffect[]{StatusEffects.POISON};

    @Override
    public String getName() {
        return "hazmatone";
    }

    @Override
    public StatusEffect[] getEffects() {
        return effects;
    }
}