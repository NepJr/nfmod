package nepjr.nf.recipe;

import com.glodblock.github.loader.FCBlocks;
import com.glodblock.github.loader.FCItems;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.Mods;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;

public class NFAssemblerRecipes 
{
	public static void start()
	{
		lv();
		mv();
		hv();
		ev();
		uv();
		luv();
		zpm();
		uv();
		uhv();
		uev();
		uiv();
		uxv();
		opv();
		max();
	}
	
	public static void lv()
	{}
	
	public static void mv()
	{}
	
	public static void hv()
	{}
	
	public static void ev()
	{
		RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
			.input(FCItems.PART_DUAL_INTERFACE, 6)
			.inputs(Mods.AppliedEnergistics2.getItem("part", 16)) // ME Glass Cable - Fluix
			.output(FCBlocks.DUAL_INTERFACE)
			.EUt(GTValues.VA[GTValues.EV])
			.duration(900)
			.buildAndRegister();
	}
	
	public static void iv()
	{}
	
	public static void luv()
	{}
	
	public static void zpm()
	{}
	
	public static void uv()
	{}
	
	public static void uhv()
	{
		RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder()
			.input(MetaTileEntities.HULL[GTValues.UHV])
			.input(OrePrefix.circuit, Tier.UHV, 4)
			.input(MetaItems.ROBOT_ARM_UHV, 4)
			.output(MetaTileEntities.AUTO_MAINTENANCE_HATCH)
			.EUt(GTValues.VA[GTValues.UHV])
			.duration(36000)
			.circuitMeta(16)
			.buildAndRegister();
	}
	
	public static void uev()
	{}
	
	public static void uiv()
	{}
	
	public static void uxv()
	{}
	
	public static void opv()
	{}
	
	public static void max()
	{}
	
	
}
