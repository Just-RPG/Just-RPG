package org.izuna.justrpg.spells;

import com.mojang.logging.LogUtils;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.CameraShakeData;
import io.redspace.ironsspellbooks.api.util.CameraShakeManager;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.izuna.justrpg.Justrpg;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Holy_Impact extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(Justrpg.MODID, "holy_impact");
    private final DefaultConfig defaultConfig;

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable(
                "ui.justrpg.holy_impact",
                this.getDamageText(spellLevel, caster), 1, 5
        ));
    }

    public Holy_Impact() {
        this.defaultConfig = (new DefaultConfig()).setMinRarity(SpellRarity.COMMON).setSchoolResource(SchoolRegistry.HOLY_RESOURCE).setMaxLevel(5).setCooldownSeconds(15.0).build();
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 8;
        this.spellPowerPerLevel = 3;
        this.castTime = 16;
        this.baseManaCost = 30;
    }

    public boolean canBeInterrupted(Player player) {
        return false;
    }

    public int getEffectiveCastTime(int spellLevel, @Nullable LivingEntity entity) {
        return this.getCastTime(spellLevel);
    }

    public CastType getCastType() {
        return CastType.LONG;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public Optional<SoundEvent> getCastStartSound() {
        return Optional.of((SoundEvent) SoundRegistry.DIVINE_SMITE_WINDUP.get());
    }

    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of((SoundEvent)SoundRegistry.DIVINE_SMITE_CAST.get());
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = 2.5F;
        float range = 2.0F;
        Vec3 smiteLocation = Utils.raycastForBlock(level, entity.getEyePosition(), entity.getEyePosition().add(entity.getForward().multiply((double)range, 0.0, (double)range)), ClipContext.Fluid.NONE).getLocation();
        Vec3 particleLocation = level.clip(new ClipContext(smiteLocation, smiteLocation.add(0.0, -2.0, 0.0), ClipContext.Block.VISUAL, ClipContext.Fluid.NONE, (Entity)null)).getLocation().add(0.0, 0.1, 0.0);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(((SchoolType)SchoolRegistry.HOLY.get()).getTargetingColor(), radius * 2.0F), particleLocation.x, particleLocation.y, particleLocation.z, 1, 0.0, 0.0, 0.0, 0.0, true);
        MagicManager.spawnParticles(level, ParticleTypes.ELECTRIC_SPARK, particleLocation.x, particleLocation.y, particleLocation.z, 100, 0.0, 0.0, 0.0, 0.5, false);
        CameraShakeManager.addCameraShake(new CameraShakeData(10, particleLocation, 10.0F));
        List<Entity> entities = level.getEntities(entity, AABB.ofSize(smiteLocation, (double)(radius * 2.5F), (double)(radius * 4.0F), (double)(radius * 2.5F)));
        Iterator var11 = entities.iterator();
        while(var11.hasNext()) {
            Entity targetEntity = (Entity)var11.next();
            int distance = (int) Objects.requireNonNull(this.getDamageSource(entity).getSourcePosition()).distanceTo(targetEntity.getPosition(1));
            if (targetEntity.isAlive() && targetEntity.isPickable() && Utils.hasLineOfSight(level, smiteLocation.add(0.0, 1.0, 0.0), targetEntity.getBoundingBox().getCenter(), true) && DamageSources.applyDamage(targetEntity, this.getDamage(spellLevel, entity)+(5-distance), this.getDamageSource(entity))) {
                int i = EnchantmentHelper.getFireAspect(entity);
                if (i > 0) {
                    targetEntity.setSecondsOnFire(i * 4);
                }

                EnchantmentHelper.doPostDamageEffects(entity, targetEntity);
            }
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return this.getSpellPower(spellLevel, entity) + Utils.getWeaponDamage(entity, MobType.UNDEAD);
    }

    private String getDamageText(int spellLevel, LivingEntity entity) {
        if (entity != null) {
            float weaponDamage = Utils.getWeaponDamage(entity, MobType.UNDEAD);
            String plus = "";
            if (weaponDamage > 0.0F) {
                plus = String.format(" (+%s)", Utils.stringTruncation((double)weaponDamage, 1));
            }

            String damage = Utils.stringTruncation((double)this.getDamage(spellLevel, entity), 1);
            return damage + plus;
        } else {
            float var10000 = this.getSpellPower(spellLevel, entity);
            return "" + var10000;
        }
    }

    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.OVERHEAD_MELEE_SWING_ANIMATION;
    }

    public AnimationHolder getCastFinishAnimation() {
        return AnimationHolder.pass();
    }
}
