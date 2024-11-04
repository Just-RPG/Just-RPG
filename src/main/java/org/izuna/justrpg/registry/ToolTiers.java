package org.izuna.justrpg.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import org.izuna.justrpg.Justrpg;
import org.izuna.justrpg.util.Tags;

import java.util.List;

public class ToolTiers {
    public static final Tier STELLIRIUM = TierSortingRegistry.registerTier(
            new ForgeTier(3,1850,8.5f,3.5f,14, Tags.Blocks.Ex,() -> Ingredient.of(ModItems.STELLIRIUM_RAW.get())),
            new ResourceLocation(Justrpg.MODID, "cosmicite"), List.of(Tiers.NETHERITE), List.of());

}
