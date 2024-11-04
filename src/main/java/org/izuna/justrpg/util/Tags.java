package org.izuna.justrpg.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.izuna.justrpg.Justrpg;

public class Tags {
    public static class Blocks {
        public static final TagKey<Block> Ex = tag("nsv");
        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(Justrpg.MODID, name));
        }
    }
    public static class Items {
        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(Justrpg.MODID, name));
        }
    }
}
