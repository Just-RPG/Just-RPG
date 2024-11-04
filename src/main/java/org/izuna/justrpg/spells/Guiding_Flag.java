package org.izuna.justrpg.spells;

import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.izuna.justrpg.Justrpg;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@AutoSpellConfig
public class Guiding_Flag extends AbstractSpell {
    private final ResourceLocation spellId = new ResourceLocation(Justrpg.MODID, "guiding_flag");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        int slowAmount = getSlowAmount(spellLevel); // 슬로우 값 계산
        int armorDecrease = getArmorDecrease(spellLevel); // 적군 방어력 감소 계산
        int healAmount = getHealAmount(spellLevel); // 아군 회복량 계산
        int armorIncrease = getArmorIncrease(spellLevel); // 아군 방어력 증가 계산

        return List.of(Component.translatable(
                "ui.justrpg.guiding_flag",
                slowAmount, armorDecrease, healAmount, armorIncrease
        ));
    }

    private int getSlowAmount(int spellLevel) {
        // 레벨에 따른 슬로우 양 계산
        return spellLevel; // 예: 레벨당 슬로우 1
    }

    private int getArmorDecrease(int spellLevel) {
        // 레벨에 따른 적군 방어력 감소
        return spellLevel * 2; // 예: 레벨당 방어력 감소 2
    }

    private int getHealAmount(int spellLevel) {
        // 레벨에 따른 아군 회복량
        return spellLevel; // 예: 레벨당 회복량 1
    }

    private int getArmorIncrease(int spellLevel) {
        // 레벨에 따른 아군 방어력 증가
        return spellLevel; // 예: 레벨당 방어력 증가 1
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(SchoolRegistry.HOLY_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(0.1)
            .build();

    public Guiding_Flag() {
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 12;
        this.spellPowerPerLevel = 1;
        this.castTime = 20;
        this.baseManaCost = 10;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }
    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return getSpellPower(spellLevel, entity) * .5f;
    }

    @Override
    public SpellDamageSource getDamageSource(@Nullable Entity projectile, Entity attacker) {
        return super.getDamageSource(projectile, attacker);
    }
}
