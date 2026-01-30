package xyz.nepjr.nfmod.common;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import xyz.nepjr.nfmod.NepFactory;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;

public class NFMaterials 
{
	public static Material Originium;
	public static Material OriginitePrime;
	public static Material Orundum;
	
	public static void init() 
	{
		Originium = new Material.Builder(NepFactory.id("originium"))
				.dust()
				.color(0x884400)
				.ore()
				.buildAndRegister();
		
		OriginitePrime = new Material.Builder(NepFactory.id("originite_prime"))
				.gem()
				.color(0xFF8800)
				.buildAndRegister();
		
		Orundum = new Material.Builder(NepFactory.id("orundum"))
				.ingot()
				.flags(GENERATE_PLATE, GENERATE_ROD, GENERATE_LONG_ROD)
				.liquid()
				.color(0xFF0000)
				.buildAndRegister();
	}
}
