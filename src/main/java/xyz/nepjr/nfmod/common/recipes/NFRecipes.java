package xyz.nepjr.nfmod.common.recipes;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import xyz.nepjr.nfmod.common.recipes.generator.NFHighTempSteamTurbineRecipes;

public class NFRecipes
{
	public static void init(Consumer<FinishedRecipe> provider)
	{
		NFHighTempSteamTurbineRecipes.init(provider);
	}
}
