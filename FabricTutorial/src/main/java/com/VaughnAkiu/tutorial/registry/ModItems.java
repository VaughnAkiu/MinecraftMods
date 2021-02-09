package com.VaughnAkiu.tutorial.registry;

import com.VaughnAkiu.tutorial.Tutorial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems
{
    //Items
    public static final Item RUBY = new Item(new Item.Settings().group(Tutorial.ITEM_GROUP));

    //Block Items
    public static final BlockItem RUBY_BLOCK = new BlockItem(ModBlocks.RUBY_BLOCK, new Item.Settings().group(Tutorial.ITEM_GROUP));

    public static void registerItems()
    {
        Registry.register(Registry.ITEM, new Identifier(Tutorial.MOD_ID, "ruby"), RUBY);
        Registry.register(Registry.ITEM, new Identifier(Tutorial.MOD_ID, "ruby_block"), RUBY_BLOCK);
    }

}
