package ru.incube.hazmat.Item.material;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;

public class IronHazmat extends BaseHazmat {
    public final StatusEffect[] effects = new StatusEffect[]{StatusEffects.NAUSEA};

    @Override
    public String getName() {
        return "hazmattwo";
    }

    @Override
    public StatusEffect[] getEffects() {
        return effects;
    }
}