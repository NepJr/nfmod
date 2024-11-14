package nepjr.nf.space.dims;

import nepjr.nf.NFTags;
import nepjr.nf.cfg.NFConfig;
import nepjr.nf.space.dims.planets.vulcanus.WorldProviderVulcanus;
import nepjr.nf.space.dims.station.overworld.WorldProviderOverworldSpaceStation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class NFDimensions 
{
	// Space Stations
	public static DimensionType overworldSpaceStation;
	public static DimensionType vulcanus;
	
	public static void init()
	{
		registerTypes();
		registerDimensions();
	}
	
	public static void registerTypes()
	{
		overworldSpaceStation = DimensionType.register(NFTags.MODID + "_space_station_overworld", "space_station_overworld", NFConfig.dimensionIDs.overworldSpaceStationId, WorldProviderOverworldSpaceStation.class, false);
		//vulcanus = DimensionType.register(NFTags.MODID + "_vulcanus", "vulcanus", NFConfig.dimensionIDs.vulcanusId, WorldProviderVulcanus.class, false);
	}
	
	public static void registerDimensions()
	{
		DimensionManager.registerDimension(NFConfig.dimensionIDs.overworldSpaceStationId, overworldSpaceStation);
		//DimensionManager.registerDimension(NFConfig.dimensionIDs.vulcanusId, vulcanus);
	}
}
