package org.izuna.justrpg.registry;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;
import org.izuna.justrpg.Justrpg;
import org.izuna.justrpg.spells.Guiding_Flag;
import org.izuna.justrpg.spells.Holy_Impact;
import org.izuna.justrpg.spells.Snipe;
import org.izuna.justrpg.spells.SuperHealSpell;

import java.util.List;

public class SpellRegistry {
    public static final DeferredRegister<AbstractSpell> SPELLS = DeferredRegister.create(io.redspace.ironsspellbooks.api.registry.SpellRegistry.SPELL_REGISTRY_KEY, Justrpg.MODID);
    public static void register(IEventBus eventBus) {
        SPELLS.register(eventBus);
    }

    public static RegistryObject<AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }

    public static final RegistryObject<AbstractSpell> SUPER_HEAL_SPELL = registerSpell(new SuperHealSpell());
    public static final RegistryObject<AbstractSpell> SNIPE = registerSpell(new Snipe());
    public static final RegistryObject<AbstractSpell> GUIDING_FLAG = registerSpell(new Guiding_Flag());
    public static final RegistryObject<AbstractSpell> HOLY_IMPACT = registerSpell(new Holy_Impact());

}
