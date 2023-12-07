package ru.incube.hazmat;

import lombok.extern.slf4j.Slf4j;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import ru.incube.hazmat.Item.material.DiamondHazmat;
import ru.incube.hazmat.Item.material.IronHazmat;
import ru.incube.hazmat.Item.material.LeatherHazmat;

@Slf4j(topic = "Registry")
public class Registry {
    private static final FabricItemSettings ITEM_SETTINGS = new FabricItemSettings();
    public static final Item DIAMOND_HELMET_HEAD = registerArmorItems("helmet", new DiamondHazmat(), EquipmentSlot.HEAD);
    public static final Item DIAMOND_CHESTPLATE_CHEST = registerArmorItems("chestplate", new DiamondHazmat(), EquipmentSlot.CHEST);
    public static final Item DIAMOND_LEGGINGS_LEGS = registerArmorItems("leggings", new DiamondHazmat(), EquipmentSlot.LEGS);
    public static final Item DIAMOND_BOOTS_FEET = registerArmorItems("boots", new DiamondHazmat(), EquipmentSlot.FEET);
    public static final Item IRON_HELMET_HEAD = registerArmorItems("helmet", new IronHazmat(), EquipmentSlot.HEAD);
    public static final Item IRON_CHESTPLATE_CHEST = registerArmorItems("chestplate", new IronHazmat(), EquipmentSlot.CHEST);
    public static final Item IRON_LEGGINGS_LEGS = registerArmorItems("leggings", new IronHazmat(), EquipmentSlot.LEGS);
    public static final Item IRON_BOOTS_FEET = registerArmorItems("boots", new IronHazmat(), EquipmentSlot.FEET);
    public static final Item LEATHER_HELMET_HEAD = registerArmorItems("helmet", new LeatherHazmat(), EquipmentSlot.HEAD);
    public static final Item LEATHER_CHESTPLATE_CHEST = registerArmorItems("chestplate", new LeatherHazmat(), EquipmentSlot.CHEST);
    public static final Item LEATHER_LEGGINGS_LEGS = registerArmorItems("leggings", new LeatherHazmat(), EquipmentSlot.LEGS);
    public static final Item LEATHER_BOOTS_FEET = registerArmorItems("boots", new LeatherHazmat(), EquipmentSlot.FEET);

    // TODO: Убрал "static", чтобы не было предупреждения "Instantiation of utility class 'Registry'" в Main классе
    private final ItemGroup GROUP = FabricItemGroupBuilder.create(new Identifier(Main.MODID, "all_item"))
            .icon(() -> new ItemStack(LEATHER_CHESTPLATE_CHEST))
            .appendItems(items -> {
                items.add(new ItemStack(LEATHER_HELMET_HEAD));
                items.add(new ItemStack(LEATHER_CHESTPLATE_CHEST));
                items.add(new ItemStack(LEATHER_LEGGINGS_LEGS));
                items.add(new ItemStack(LEATHER_BOOTS_FEET));
                items.add(new ItemStack(IRON_HELMET_HEAD));
                items.add(new ItemStack(IRON_CHESTPLATE_CHEST));
                items.add(new ItemStack(IRON_LEGGINGS_LEGS));
                items.add(new ItemStack(IRON_BOOTS_FEET));
                items.add(new ItemStack(DIAMOND_HELMET_HEAD));
                items.add(new ItemStack(DIAMOND_CHESTPLATE_CHEST));
                items.add(new ItemStack(DIAMOND_LEGGINGS_LEGS));
                items.add(new ItemStack(DIAMOND_BOOTS_FEET));
            })
            .build();

    private static Item registerItem(String ID, Item item) {
        return net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, new Identifier(Main.MODID, ID), item);
    }

    private static Item registerArmorItems(String name, ArmorMaterial material, EquipmentSlot slot) {
        String materialName = material.getClass().getSimpleName().toLowerCase().replace("hazmat", "");
        return registerItem(name + "_" + materialName + "_" + slot.getName(), new ArmorItem(material, slot, ITEM_SETTINGS));
    }
}