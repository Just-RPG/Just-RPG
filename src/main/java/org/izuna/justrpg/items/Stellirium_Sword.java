package org.izuna.justrpg.items;

import io.redspace.ironsspellbooks.api.item.weapons.MagicSwordItem;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.api.registry.SpellDataRegistryHolder;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Rarity;
import org.izuna.justrpg.registry.ToolTiers;
import org.izuna.justrpg.util.ItemPropertiesHelper;

import java.util.Map;
import java.util.UUID;

public class Stellirium_Sword extends MagicSwordItem {
    public Stellirium_Sword(SpellDataRegistryHolder[] holder) {
        super(ToolTiers.STELLIRIUM, 6, -0.0f, holder,
                Map.of(
                        AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("212b5a66-2b43-4c18-ab05-6de0cc4d64d3"), "Weapon Modifier", 1, AttributeModifier.Operation.MULTIPLY_BASE)
                ),
                ItemPropertiesHelper.equipment().rarity(Rarity.EPIC));
    }
}
