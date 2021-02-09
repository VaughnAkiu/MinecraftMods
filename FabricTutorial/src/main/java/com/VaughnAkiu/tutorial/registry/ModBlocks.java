package com.VaughnAkiu.tutorial.registry;

import com.VaughnAkiu.tutorial.Tutorial;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks
{
    //can keep adding settings to the blocks through this line
    public static final Block RUBY_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 2)        //iron tools and above
            .requiresTool()
            .strength(5.0f,30.0f)                     //how hard to break, and blast resistance
            .sounds(BlockSoundGroup.METAL)
            .luminance(1)                                             //amount block glows
            );

    public static void registerBlocks()
    {
        Registry.register(Registry.BLOCK, new Identifier(Tutorial.MOD_ID, "ruby_block"), RUBY_BLOCK);
    }
}
