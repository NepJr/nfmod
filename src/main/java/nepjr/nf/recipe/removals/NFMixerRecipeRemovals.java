package nepjr.nf.recipe.removals;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class NFMixerRecipeRemovals 
{
	
	public static void start()
	{
		
		// Black Steel x 5
		GTRecipeHandler.removeRecipesByInputs(RecipeMaps.MIXER_RECIPES, 
				new ItemStack[] {
						OreDictUnifier.get(OrePrefix.dust, Materials.BlackBronze, 1),
						OreDictUnifier.get(OrePrefix.dust, Materials.Nickel, 1),
						OreDictUnifier.get(OrePrefix.dust, Materials.Steel, 3),
						IntCircuitIngredient.getIntegratedCircuit(1)
						}, 
				new FluidStack[] {});
	}
}
