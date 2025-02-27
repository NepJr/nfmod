package nepjr.nf.api.util;

import java.util.ArrayList;
import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import nepjr.nf.space.dims.NFDimensions;
import net.minecraft.world.DimensionType;

public class MaterialUtil 
{
	protected static ArrayList<int[]> materialRegistry = new ArrayList<int[]>();
	
	public static void buildMaterialRegistry()
	{
		/*
		 *  Ensures the list is populated. 32767 is the max amount of materials you can have
		 *  If this is greater than 32767, something gone hella wrong tbh
		 */
		
		for(int i = 0; i < 32767; i++)
		{
			materialRegistry.add(new int[] {0, 0});
		}
		
		/*
		 * Low Voltage materials
		 */
		
		addToRegistry(Materials.Steel, GTValues.LV, NFDimensions.overworldSpaceStation);
		
		/*
		 * Medium Voltage materials
		 */
		addToRegistry(Materials.Aluminium, GTValues.MV, DimensionType.OVERWORLD);
		
		/*
		 * High Voltage materials
		 */
		addToRegistry(Materials.StainlessSteel, GTValues.HV, DimensionType.OVERWORLD);
		
		/*
		 * Extreme Voltage materials
		 */
		addToRegistry(Materials.Titanium, GTValues.EV, DimensionType.OVERWORLD);
		
		/*
		 * Insane Voltage materials
		 */
		addToRegistry(Materials.TungstenSteel, GTValues.IV, DimensionType.OVERWORLD);
		
		/*
		 * Ludicrous Voltage materials
		 */
		
		/*
		 * Zero Point Module materials
		 */
		
		/*
		 * Ultimate Voltage materials
		 */
		
		/*
		 * Ultra High Voltage materials
		 */
		
		/*
		 * Ultra Excessive Voltage materials
		 */
		
		/*
		 * Ultra Immense Voltage materials
		 */
		
		/*
		 * Ultra Extreme Voltage materials
		 */
		
		/*
		 * Overpowered Voltage materials
		 */
		
		/*
		 * Maximum materials
		 */
		
		/*
		 * Unassigned materials
		 */
		if(materialRegistry.size() > 32767)
		{
			throw new ArrayIndexOutOfBoundsException("Material Registry is larger than the maximum materials allowed in GregTech!!! Something has gone horribly wrong!!! If you're not a dev and are seeing this message, let NepJr know!!!");
		}
		
	}
	
	public static int getMaterialTier(Material material)
	{
		return materialRegistry.get(material.getId())[0];
	}
	
	public int getMaterialPlanetDimensionId(Material material)
	{
		return materialRegistry.get(material.getId())[1];
	}
	
	private static void addToRegistry(Material material, int tier, DimensionType type)
	{
		materialRegistry.set(material.getId(), new int[] {tier, type.getId()});
	}
}
