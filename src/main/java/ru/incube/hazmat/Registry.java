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
    public static final Item HELMET_TREE_HAZMAT = registerArmorItems("helmet", new DiamondHazmat(), EquipmentSlot.HEAD);
    public static final Item CHESTPLATE_TREE_HAZMAT = registerArmorItems("chestplate", new DiamondHazmat(), EquipmentSlot.CHEST);
    public static final Item LEGGINGS_TREE_HAZMAT = registerArmorItems("legging", new DiamondHazmat(), EquipmentSlot.LEGS);
    public static final Item BOOTS_TREE_HAZMAT = registerArmorItems("boot", new DiamondHazmat(), EquipmentSlot.FEET);
    public static final Item HELMET_TWO_HAZMAT = registerArmorItems("helmet", new IronHazmat(), EquipmentSlot.HEAD);
    public static final Item CHESTPLATE_TWO_HAZMAT = registerArmorItems("chestplate", new IronHazmat(), EquipmentSlot.CHEST);
    public static final Item LEGGINGS_TWO_HAZMAT = registerArmorItems("legging", new IronHazmat(), EquipmentSlot.LEGS);
    public static final Item BOOTS_TWO_HAZMAT = registerArmorItems("boot", new IronHazmat(), EquipmentSlot.FEET);
    public static final Item HELMET_ONE_HAZMAT = registerArmorItems("helmet", new LeatherHazmat(), EquipmentSlot.HEAD);
    public static final Item CHESTPLATE_ONE_HAZMAT = registerArmorItems("chestplate", new LeatherHazmat(), EquipmentSlot.CHEST);
    public static final Item LEGGINGS_ONE_HAZMAT = registerArmorItems("legging", new LeatherHazmat(), EquipmentSlot.LEGS);
    public static final Item BOOTS_ONE_HAZMAT = registerArmorItems("boot", new LeatherHazmat(), EquipmentSlot.FEET);

    // TODO: Убрал "static", чтобы не было предупреждения "Instantiation of utility class 'Registry'" в Main классе
    private final ItemGroup GROUP = FabricItemGroupBuilder.create(new Identifier(Main.MODID, "all_Item"))
            .icon(() -> new ItemStack(CHESTPLATE_ONE_HAZMAT))
            .appendItems(items -> {
                items.add(new ItemStack(HELMET_ONE_HAZMAT));
                items.add(new ItemStack(CHESTPLATE_ONE_HAZMAT));
                items.add(new ItemStack(LEGGINGS_ONE_HAZMAT));
                items.add(new ItemStack(BOOTS_ONE_HAZMAT));
                items.add(new ItemStack(HELMET_TWO_HAZMAT));
                items.add(new ItemStack(CHESTPLATE_TWO_HAZMAT));
                items.add(new ItemStack(LEGGINGS_TWO_HAZMAT));
                items.add(new ItemStack(BOOTS_TWO_HAZMAT));
                items.add(new ItemStack(HELMET_TREE_HAZMAT));
                items.add(new ItemStack(CHESTPLATE_TREE_HAZMAT));
                items.add(new ItemStack(LEGGINGS_TREE_HAZMAT));
                items.add(new ItemStack(BOOTS_TREE_HAZMAT));
            })
            .build();

    private static Item registerItem(String ID, Item item) {
        return net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, new Identifier(Main.MODID, ID), item);
    }

    private static Item registerArmorItems(String name, ArmorMaterial material, EquipmentSlot slot) {
        return registerItem(name + "_" + slot.getName() + "_hazmat", new ArmorItem(material, slot, ITEM_SETTINGS));
    }
}