package xyz.nepjr.nfmod.common.recipes.generator;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import xyz.nepjr.nfmod.common.NFMaterials;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.DistilledWater;
import static xyz.nepjr.nfmod.common.recipes.NFRecipeTypes.HIGH_TEMP_STEAM_TURBINE;

public class NFHighTempSteamTurbineRecipes 
{
	public static void init(Consumer<FinishedRecipe> provider)
	{
		HIGH_TEMP_STEAM_TURBINE.recipeBuilder("steam")
        .inputFluids(NFMaterials.HighTempSteam.getFluid(1))
        .outputFluids(DistilledWater.getFluid(1))
        .duration(100)
        .EUt(-V[EV])
        .save(provider);
	}
}
