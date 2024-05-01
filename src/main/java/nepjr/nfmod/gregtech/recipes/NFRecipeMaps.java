package nepjr.nfmod.gregtech.recipes;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget.MoveType;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

import crafttweaker.annotations.ZenRegister;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.nfmod.recipe.NFRecipeMaps")
@ZenRegister
public class NFRecipeMaps 
{
    /**
     * Example:
     * 
     * <pre>
     * NFRecipeMaps.ORE_CRUSHER.recipeBuilder()
     *         .inputs(new ItemStack(Items.CHICKEN))
     *         .output(OrePrefix.dust, Materials.Meat)
     *         .output(OrePrefix.dustTiny, Materials.Bone)
     *         .EUt(15).duration(102).buildAndRegister();
     * </pre>
     */
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> ORE_CRUSHER = new RecipeMap<>("ore_crusher", 1, 4, 0, 0,
            new SimpleRecipeBuilder(), false)
                    .setSlotOverlay(false, false, GuiTextures.CRUSHED_ORE_OVERLAY)
                    .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
                    .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, MoveType.HORIZONTAL)
                    .setSound(GTSoundEvents.MACERATOR);
    
    public static final RecipeMap<SimpleRecipeBuilder> EMPTY = new RecipeMap<>("empty", 1, 0, 1, 0,
    		new SimpleRecipeBuilder(), false)
    				.setSound(GTSoundEvents.ARC);
}
