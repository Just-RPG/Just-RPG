package org.izuna.justrpg.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.izuna.justrpg.Justrpg;
import org.izuna.justrpg.blocks.*;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;


public class ModBlocks {



    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Justrpg.MODID);

    public static final RegistryObject<Block> STELLIRIUM_ORE = registerBlock("stellirium_ore",() -> new Stellirium_Ore(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DEEPSLATE_STELLIRIUM_ORE = registerBlock("deepslate_stellirium_ore",() -> new Deepslate_Stellirium_Ore(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops().strength(30.0F, 1200.0F)));
    public static final RegistryObject<Block> TESTBLOCK = registerBlock("testblock",() -> new TestBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));
    public static final RegistryObject<Block> GUIDING_FLAG_CIRCLE = registerBlock("guiding_flag_circle",() -> new Block(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return (Boolean) p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
        };
    }

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> blockObj = BLOCKS.register(name, block);
        registerBlockItem(name, blockObj);
        return blockObj;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(final String name, final RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
