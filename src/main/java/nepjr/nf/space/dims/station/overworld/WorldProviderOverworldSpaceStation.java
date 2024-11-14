package nepjr.nf.space.dims.station.overworld;

import nepjr.nf.api.space.dims.NFWorldProviderSpaceStation;
import nepjr.nf.cfg.NFConfig;
import nepjr.nf.space.dims.NFDimensions;
import net.minecraft.world.DimensionType;

public class WorldProviderOverworldSpaceStation extends NFWorldProviderSpaceStation {

	@Override
	public DimensionType getDimensionType() {
		return NFDimensions.overworldSpaceStation;
	}
	
	@Override
	public String getSaveFolder()
	{
		return "SPACESTATION_OVERWORLD";
	}

	@Override
	public float getSolarEfficiency() 
	{
		return NFConfig.solarEfficiencyOptions.overworldSpaceStationSolarEfficiency;
	}
}
