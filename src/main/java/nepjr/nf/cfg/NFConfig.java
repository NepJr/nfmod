package nepjr.nf.cfg;

import nepjr.nf.NFTags;
import net.minecraftforge.common.config.Config;

@Config(modid = NFTags.MODID)
public class NFConfig 
{
	@Config.Name("Machine Options")
	@Config.Comment("General Options regarding machines")
	public static MachineOptions machineOptions = new MachineOptions();
	
	@Config.Name("Dimension ID Options")
	@Config.Comment("Shouldn't need to touch this but just incase conflicts are happening")
	@Config.RequiresMcRestart
	public static DimensionIDs dimensionIDs = new DimensionIDs();
	
	@Config.Name("Planet Options")
	@Config.Comment("General Options regarding Planets")
	public static PlanetOptions planetOptions = new PlanetOptions();
	
	@Config.Name("Solar Efficiency Options")
	@Config.Comment("General Options regarding Solar Efficiency on other Planets")
	public static SolarEfficiencyOptions solarEfficiencyOptions = new SolarEfficiencyOptions();
	
	public static class MachineOptions
	{
		@Config.Name("Advanced Parallel Limit")
		@Config.Comment("Controls the maximum parallel multiblocks can preform")
		@Config.RequiresMcRestart
		public int parallelLimitAdvanced = 1024;
		
		@Config.Name("Supercomputer Parallel Limit")
		@Config.Comment("Controls the maximum parallel multiblocks can preform")
		@Config.RequiresMcRestart
		public int parallelLimitSupercomputer = 4096;
		
		@Config.Name("Quantumcomputer Parallel Limit")
		@Config.Comment("Controls the maximum parallel multiblocks can preform")
		@Config.RequiresMcRestart
		public int parallelLimitQuantumcomputer = 16384;
		
		@Config.Name("Cosmic Parallel Limit")
		@Config.Comment("Controls the maximum parallel multiblocks can preform")
		@Config.RequiresMcRestart
		public int parallelLimitCosmic = 65535;
	}
	
	public static class PlanetOptions
	{
	}
	
	public static class DimensionIDs
	{
		@Config.Name("Overworld Space Station Dimension ID")
		public int overworldSpaceStationId = 420;
		
		@Config.Name("Vulcanus Dimension ID")
		public int vulcanusId = 666;
	}
	
	public static class SolarEfficiencyOptions
	{
		@Config.Name("Overworld Solar Efficiency")
		@Config.Comment("Set the solar efficiency for the Overworld")
		public float overworldSolarEfficiency = 1.0f;
		
		@Config.Name("Overworld Space Station Solar Efficiency")
		@Config.Comment("Set the solar efficiency for the Overworld Space Station")
		public float overworldSpaceStationSolarEfficiency = 5.0f;
	}
}
