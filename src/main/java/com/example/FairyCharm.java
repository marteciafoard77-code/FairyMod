package com.example;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class FairyCharm extends Item {

    public FairyCharm() {
        super(new FabricItemSettings()
                .group(ItemGroup.MISC) // Puts the item in the Miscellaneous tab
                .maxCount(1));         // Only one per stack
    }
}
