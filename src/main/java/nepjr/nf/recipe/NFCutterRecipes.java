package nepjr.nf.recipe;

import com.glodblock.github.loader.FCBlocks;
import com.glodblock.github.loader.FCItems;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;

public class NFCutterRecipes 
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
		RecipeMaps.CUTTER_RECIPES.recipeBuilder()
		.input(FCBlocks.DUAL_INTERFACE)
		.output(FCItems.PART_DUAL_INTERFACE, 6)
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
	{}
	
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
