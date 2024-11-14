package nepjr.nf.recipe;

import nepjr.nf.recipe.removals.NFRecipeRemovals;

public class NFRecipes 
{
	public static void register()
	{
		// Removals need to happen first, otherwise bad things might happen
		NFRecipeRemovals.start();
		
		// Now we can actually add recipes
		NFAssemblerRecipes.start();
		NFCutterRecipes.start();
	}
}
