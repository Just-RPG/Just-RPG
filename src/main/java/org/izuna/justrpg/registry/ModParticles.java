package org.izuna.justrpg.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.izuna.justrpg.Justrpg;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Justrpg.MODID);
    public static final RegistryObject<SimpleParticleType> TEST_PARTICLE = PARTICLES.register("test_particles", () -> new SimpleParticleType(false));
    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}