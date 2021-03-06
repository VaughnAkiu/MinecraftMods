package com.VaughnAkiu.tutorial;

import com.VaughnAkiu.tutorial.registry.ModBlocks;
import com.VaughnAkiu.tutorial.registry.ModItems;
import jdk.vm.ci.meta.Constant;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.Blocks;
import net.minecraft.client.gl.Uniform;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.util.Identifier;

public class Tutorial implements ModInitializer
{
    public static final String MOD_ID = "tutorial";

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "general"),
            ()-> new ItemStack(ModItems.RUBY));

    public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "other"))
            .icon(()-> new ItemStack(Blocks.ENCHANTING_TABLE))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(ModBlocks.RUBY_BLOCK));
                stacks.add(new ItemStack(Items.APPLE));
                stacks.add(new ItemStack(ModItems.RUBY));
                stacks.add(new ItemStack(Items.GLOWSTONE_DUST));
            })
            .build();

    //grabbing emerald ore loot table id
    private static final Identifier EMERALD_ORE_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/emerald_ore");
    private static final Identifier RUBY_BLOCK_LOOT_TABLE_ID = new Identifier(MOD_ID, "blocks/ruby_block");


    @Override
    public void onInitialize()
    {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        modifyLootTables();
    }

    private void modifyLootTables()
    {
        LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, identifier, fabricLootSupplierBuilder, lootTableSetter) ->
        {   //checks for emerald ore loot table
            if (EMERALD_ORE_LOOT_TABLE_ID.equals(identifier)){
                //add single individual item
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(1))
                        .with(ItemEntry.builder(Items.GOLD_NUGGET))
                        .withFunction(SetCountLootFunction.builder(UniformLootTableRange.between(1.0f, 4.0f)).build());
                fabricLootSupplierBuilder.withPool(poolBuilder.build());

                //add custom loot table
                FabricLootPoolBuilder poolBuilderTwo = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootTableRange.create(1))
                        .with(LootTableEntry.builder(RUBY_BLOCK_LOOT_TABLE_ID));
                fabricLootSupplierBuilder.withPool(poolBuilderTwo.build());
            }
        }));
    }
}
