package org.izuna.justrpg.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.izuna.justrpg.Justrpg;
import org.izuna.justrpg.registry.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class TestPolishingCategory implements IRecipeCategory<TestPolishingCategory> {
    public static final ResourceLocation UID = new ResourceLocation(Justrpg.MODID, "test_polishing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(Justrpg.MODID,
            "textures/gui/test_polishing.png");
    public static final RecipeType<TestPolishingCategory> TEST_POLISHING_TYPE = new RecipeType<>(UID, TestPolishingCategory.class);
    private final IDrawable background;
    private final IDrawable icon;
    public TestPolishingCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.TESTBLOCK.get()));
    }
    @Override
    public RecipeType<TestPolishingCategory> getRecipeType() {
        return null;
    }

    @Override
    public Component getTitle() {
        return null;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return null;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, TestPolishingCategory testPolishingCategory, IFocusGroup iFocusGroup) {

    }
}
