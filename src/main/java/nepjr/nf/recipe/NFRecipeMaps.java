package nepjr.nf.recipe;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget.MoveType;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;
import stanhebben.zenscript.annotations.ZenProperty;

public class NFRecipeMaps 
{
	@ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> ORE_CRUSHER_RECIPES = new RecipeMap<>("ore_crusher", 1, 4, 0,
            0, new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.SLOT)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.MACERATOR);
}
