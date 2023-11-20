package ru.vkprofi.hazmat;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.vkprofi.hazmat.Item.material.DimondHazmat;
import ru.vkprofi.hazmat.Item.material.IronHazmat;
import ru.vkprofi.hazmat.Item.material.LeatherHazmat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class registry {
    public static Logger log = LogManager.getLogger("[Hazmat] (registry)");  // Log4j для вывода в консоль от класса

    // Хазмат лвл 3
    public static final Item HELMET_TREE_HAZMAT = registryItem("helmet_tree_hazmat", new ArmorItem(new DimondHazmat(), EquipmentSlot.HEAD, new FabricItemSettings()));
    public static final Item CHESTPLATE_TREE_HAZMAT = registryItem("chestplate_tree_hazmat", new ArmorItem(new DimondHazmat(), EquipmentSlot.CHEST, new FabricItemSettings()));
    public static final Item LEGGINGS_TREE_HAZMAT = registryItem("legging_tree_hazmat", new ArmorItem(new DimondHazmat(), EquipmentSlot.LEGS, new FabricItemSettings()));
    public static final Item BOOTS_TREE_HAZMAT = registryItem("boot_tree_hazmat", new ArmorItem(new DimondHazmat(), EquipmentSlot.FEET, new FabricItemSettings()));

    // Хазмат лвл 2
    public static final Item HELMET_TWO_HAZMAT = registryItem("helmet_two_hazmat", new ArmorItem(new IronHazmat(), EquipmentSlot.HEAD, new FabricItemSettings()));
    public static final Item CHESTPLATE_TWO_HAZMAT = registryItem("chestplate_two_hazmat", new ArmorItem(new IronHazmat(), EquipmentSlot.CHEST, new FabricItemSettings()));
    public static final Item LEGGINGS_TWO_HAZMAT = registryItem("legging_two_hazmat", new ArmorItem(new IronHazmat(), EquipmentSlot.LEGS, new FabricItemSettings()));
    public static final Item BOOTS_TWO_HAZMAT = registryItem("boot_two_hazmat", new ArmorItem(new IronHazmat(), EquipmentSlot.FEET, new FabricItemSettings()));

    // Хазмат лвл 1
    public static final Item HELMET_ONE_HAZMAT = registryItem("helmet_one_hazmat", new ArmorItem(new LeatherHazmat(), EquipmentSlot.HEAD, new FabricItemSettings()));
    public static final Item CHESTPLATE_ONE_HAZMAT = registryItem("chestplate_one_hazmat", new ArmorItem(new LeatherHazmat(), EquipmentSlot.CHEST, new FabricItemSettings()));
    public static final Item LEGGINGS_ONE_HAZMAT = registryItem("legging_one_hazmat", new ArmorItem(new LeatherHazmat(), EquipmentSlot.LEGS, new FabricItemSettings()));
    public static final Item BOOTS_ONE_HAZMAT = registryItem("boot_one_hazmat", new ArmorItem(new LeatherHazmat(), EquipmentSlot.FEET, new FabricItemSettings()));

    //группа
    private static final ItemGroup group = FabricItemGroupBuilder.create(new Identifier(main.MODID,"all_Item"))
            .icon((Supplier<ItemStack>) CHESTPLATE_ONE_HAZMAT)
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
    public registry(){}
    private static Item registryItem(String ID, Item item){
        return Registry.register(Registry.ITEM,new Identifier(main.MODID,ID),item);
    }
}
