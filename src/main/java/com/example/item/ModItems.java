package com.example.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import com.example.ExampleMod;

public class ModItems {

    public static final Item FAIRY_CHARM = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID, "fairy_charm"), FAIRY_CHARM);
    }
}

