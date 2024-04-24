package nepjr.nfmod.gregtech.recipes.uhv;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import nepjr.nfmod.gregtech.materials.NFMaterials;

public class NFDistillationTowerRecipesUHV 
{
	public static void begin()
	{
		RecipeMaps.DISTILLATION_RECIPES.recipeBuilder()
	              .fluidInputs(NFMaterials.sulfuricOriginium.getFluid(8000))
	              .output(OrePrefix.dustTiny, NFMaterials.originitePrime)
	              .fluidOutputs(NFMaterials.originium.getFluid(144))
	              .fluidOutputs(NFMaterials.chimite.getFluid(288))
	              .fluidOutputs(Materials.DilutedSulfuricAcid.getFluid(4000))
	              .fluidOutputs(Materials.Naquadah.getFluid(7568))
	              .duration(6000)
	              .EUt(GTValues.VA[GTValues.UHV])
	              .buildAndRegister();
	}
}
