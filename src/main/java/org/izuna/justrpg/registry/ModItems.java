package org.izuna.justrpg.registry;

import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.izuna.justrpg.Justrpg;
import org.izuna.justrpg.items.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Justrpg.MODID);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static final RegistryObject<Item> TESTITEM = ITEMS.register("testitem",() -> new TestItem(new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(SpellRegistry.HOLY_IMPACT, 1)}));
    public static final RegistryObject<Item> HOLY_SWORD = ITEMS.register("holy_sword",() -> new Holy_Sword(new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(SpellRegistry.HOLY_IMPACT, 1)}));
    public static final RegistryObject<Item> STELLIRIUM_RAW = ITEMS.register("raw_stellirium",() -> new Stellirium_Raw(new Item.Properties()));
    public static final RegistryObject<Item> STELLIRIUM_INGOT = ITEMS.register("stellirium_ingot",() -> new Stellirium_Ingot(new Item.Properties()));
    public static final RegistryObject<Item> STELLIRIUM_PICKAXE = ITEMS.register("stellirium_pickaxe",()->new Stellirium_Pickaxe(ToolTiers.STELLIRIUM,1,-2.8f,new Item.Properties()));
    public static final RegistryObject<Item> STELLIRIUM_SWORD = ITEMS.register("stellirium_sword",() -> new Stellirium_Sword(new SpellDataRegistryHolder[]{new SpellDataRegistryHolder(SpellRegistry.HOLY_IMPACT, 1)}));

}
