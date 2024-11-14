package nepjr.nf.api.util;

import nepjr.nf.api.space.dims.NFWorldProvider;
import nepjr.nf.cfg.NFConfig;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;

public class DimensionUtil 
{
	public static float getSolarEfficiency(WorldProvider provider)
	{
		if(provider instanceof NFWorldProvider)
		{
			NFWorldProvider spaceProvider = (NFWorldProvider) provider;
			return spaceProvider.getSolarEfficiency();
		}
		if(provider instanceof WorldProviderHell || provider instanceof WorldProviderEnd)
		{
			return 0;
		}
		return 1;
	}
	
	public static float getSolarEfficiencyDimId(int id)
	{
		if(id == NFConfig.dimensionIDs.overworldSpaceStationId) // Overworld Space Station
		{
			return NFConfig.solarEfficiencyOptions.overworldSpaceStationSolarEfficiency;
		}
		if(id == -1 || id == 1) // We don't want the nether or the end
		{
			return 0;
		}
		return 1;
	}
}
