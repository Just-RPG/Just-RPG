package org.izuna.justrpg.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.izuna.justrpg.Justrpg;
import org.izuna.justrpg.particle.TestParticle;
import org.izuna.justrpg.registry.ModParticles;

@Mod.EventBusSubscriber(modid = Justrpg.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.TEST_PARTICLE.get(), TestParticle.Provider::new);
    }
}
