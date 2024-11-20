package nepjr.nf.common.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static nepjr.nf.NepFactory.nfId;

import gregtech.api.GTValues;
import nepjr.nf.api.data.EnumMultiTier;
import nepjr.nf.common.metatileentities.electric.MetaTileEntityWeatherRepeller;
import nepjr.nf.common.metatileentities.multiblock.generator.MetaTileEntitySolarArray;
import nepjr.nf.common.metatileentities.multiblock.normal.MetaTileEntityAdvancedCircuitAssembler;
import nepjr.nf.common.metatileentities.multiblock.normal.MetaTileEntityLargeCentrifugingMachine;
import nepjr.nf.common.metatileentities.multiblock.part.MetaTileEntitySolarPanelPart;

public class NFMetaTileEntities
{
	// Multiblock Generators
	public static MetaTileEntitySolarArray[] SOLAR_ARRAY = new MetaTileEntitySolarArray[GTValues.V.length];
	
	// Multiblock Machines
	public static MetaTileEntityLargeCentrifugingMachine LARGE_CENTRIFUGE;
	public static MetaTileEntityLargeCentrifugingMachine LARGE_CENTRIFUGE_TEST;
	public static MetaTileEntityAdvancedCircuitAssembler ADVANCED_CIRCUIT_ASSEMBLER;
	// Multiblock Parts
	public static MetaTileEntitySolarPanelPart[] SOLAR_PANEL_PART = new MetaTileEntitySolarPanelPart[GTValues.V.length];
	
	// Special Singleblock Machines
	public static MetaTileEntityWeatherRepeller WEATHER_REPELLER;
	
	public static void init()
	{
		WEATHER_REPELLER = registerMetaTileEntity(4000, new MetaTileEntityWeatherRepeller(nfId("weather_repeller")));
		
		LARGE_CENTRIFUGE = registerMetaTileEntity(4001, new MetaTileEntityLargeCentrifugingMachine(nfId("large_centrifuge"), EnumMultiTier.ADVANCED));
		LARGE_CENTRIFUGE_TEST = registerMetaTileEntity(4003, new MetaTileEntityLargeCentrifugingMachine(nfId("large_centrifuge_test"), EnumMultiTier.SUPERCOMPUTER));
		ADVANCED_CIRCUIT_ASSEMBLER = registerMetaTileEntity(4002, new MetaTileEntityAdvancedCircuitAssembler(nfId("advanced_circuit_assembler"), EnumMultiTier.ADVANCED));
		
        for (int i = 1; i < GTValues.V.length; i++) {
        	SOLAR_PANEL_PART[i] = new MetaTileEntitySolarPanelPart(nfId("solar_panel_part." + GTValues.VN[i].toLowerCase()), i);
        	SOLAR_ARRAY[i] = new MetaTileEntitySolarArray(nfId("solar_array." + GTValues.VN[i]), i);
            registerMetaTileEntity(4100 + i, SOLAR_PANEL_PART[i]);
            registerMetaTileEntity(4120 + i, SOLAR_ARRAY[i]);
        }
	}
}
