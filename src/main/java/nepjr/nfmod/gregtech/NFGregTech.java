package nepjr.nfmod.gregtech;

import nepjr.nfmod.gregtech.metatileentities.NFMetaTileEntities;
import nepjr.nfmod.gregtech.recipes.NFRecipes;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class NFGregTech 
{
	public static void preInit(FMLPreInitializationEvent event)
	{
		NFMetaTileEntities.init();
	}
	
	public static void init(FMLInitializationEvent event)
	{
		NFRecipes.initRecipes();
	}
}
