package nepjr.nf.recipe.removals;

import gregtech.api.recipes.ModHandler;

public class NFRecipeRemovals 
{
	public static void start()
	{
		removeCraftingTableRecipes();
		removeFurnaceRecipes();
		NFMixerRecipeRemovals.start();
	}
	
	public static void removeCraftingTableRecipes()
	{
		// GregTech Automatic Maintenance Hatches
		ModHandler.removeRecipeByName("gregtech:maintenance_hatch_automatic");
		ModHandler.removeRecipeByName("gregtech:maintenance_hatch_cleaning");
		
		// Dual Interface Block to Panel
		ModHandler.removeRecipeByName("ae2fc:part_dual_interface");
		ModHandler.removeRecipeByName("ae2fc:dual_interface_alter");
	}
	
	public static void removeFurnaceRecipes()
	{
		
	}
}
