package xyz.nepjr.nfmod.common;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import xyz.nepjr.nfmod.NepFactory;

public class NFMaterials 
{
	public static Material Originium;
	
	public static void init() 
	{
		Originium = new Material.Builder(NepFactory.id("originium"))
				.gem()
				.color(0xFF8800)
				.ore()
				.buildAndRegister();
	}
}
