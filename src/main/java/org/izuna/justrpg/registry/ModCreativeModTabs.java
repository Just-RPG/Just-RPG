package org.izuna.justrpg.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.izuna.justrpg.Justrpg;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Justrpg.MODID);
    public static final RegistryObject<CreativeModeTab> RPG_TAB = CREATIVE_MODE_TABS.register("easyrpg_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HOLY_SWORD.get())).title(Component.translatable("creativetab.rpg_tab")).displayItems((pParameters, pOutput)-> {
                pOutput.accept(ModItems.STELLIRIUM_PICKAXE.get());
                pOutput.accept(ModItems.STELLIRIUM_SWORD.get());
                pOutput.accept(ModItems.HOLY_SWORD.get());
                pOutput.accept(ModBlocks.DEEPSLATE_STELLIRIUM_ORE.get());
                pOutput.accept(ModItems.STELLIRIUM_RAW.get());
                pOutput.accept(ModItems.STELLIRIUM_INGOT.get());
            }).build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
