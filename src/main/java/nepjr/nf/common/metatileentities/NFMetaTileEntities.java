package nepjr.nf.common.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static gregtech.common.metatileentities.MetaTileEntities.getMidTier;
import static gregtech.common.metatileentities.MetaTileEntities.getHighTier;
import static nepjr.nf.NepFactory.nfId;

import java.util.function.BiFunction;
import java.util.function.Function;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import nepjr.nf.api.data.EnumMultiTier;
import nepjr.nf.common.metatileentities.electric.MetaTileEntityWeatherRepeller;
import nepjr.nf.common.metatileentities.multiblock.generator.MetaTileEntitySolarArray;
import nepjr.nf.common.metatileentities.multiblock.normal.MetaTileEntityAdvancedCircuitAssembler;
import nepjr.nf.common.metatileentities.multiblock.normal.MetaTileEntityLargeCentrifugingMachine;
import nepjr.nf.common.metatileentities.multiblock.part.MetaTileEntitySolarPanelPart;
import nepjr.nf.recipe.NFRecipeMaps;
import net.minecraft.util.ResourceLocation;

public class NFMetaTileEntities
{
	// Basic singleblock machines
	public static SimpleMachineMetaTileEntity[] ORE_CRUSHER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];;
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
		
		registerSimpleMetaTileEntity(ORE_CRUSHER, 5000, "ore_crusher",
                NFRecipeMaps.ORE_CRUSHER_RECIPES, Textures.THERMAL_CENTRIFUGE_OVERLAY, true);
		
        for (int i = 1; i < GTValues.V.length; i++) {
        	SOLAR_PANEL_PART[i] = new MetaTileEntitySolarPanelPart(nfId("solar_panel_part." + GTValues.VN[i].toLowerCase()), i);
        	SOLAR_ARRAY[i] = new MetaTileEntitySolarArray(nfId("solar_array." + GTValues.VN[i]), i);
            registerMetaTileEntity(4100 + i, SOLAR_PANEL_PART[i]);
            registerMetaTileEntity(4120 + i, SOLAR_ARRAY[i]);
        }
	}
	private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines,
            int startId,
            String name,
            RecipeMap<?> map,
            ICubeRenderer texture,
            boolean hasFrontFacing,
            Function<Integer, Integer> tankScalingFunction) {
				registerSimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing, GTUtility::gregtechId,
						tankScalingFunction);
	}

	private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines,
            int startId,
            String name,
            RecipeMap<?> map,
            ICubeRenderer texture,
            boolean hasFrontFacing) {
				registerSimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing,
						GTUtility.defaultTankSizeFunction);
	}

	public static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines,
           int startId,
           String name,
           RecipeMap<?> map,
           ICubeRenderer texture,
           boolean hasFrontFacing,
           Function<String, ResourceLocation> resourceId,
           Function<Integer, Integer> tankScalingFunction) {
				registerMetaTileEntities(machines, startId, name,
						(tier, voltageName) -> new SimpleMachineMetaTileEntity(
						resourceId.apply(String.format("%s.%s", name, voltageName)), map, texture, tier, hasFrontFacing,
						tankScalingFunction));
	}

	/**
	 * @param mteCreator Takes tier and voltage name for the machine, and outputs MTE to register
	 */
	public static void registerMetaTileEntities(
       MetaTileEntity[] machines,
       int startId,
       String name,
       BiFunction<Integer, String, MetaTileEntity> mteCreator) {
			for (int i = 0; i < machines.length - 1; i++) {
				if (i > 4 && !getMidTier(name)) continue;
				if (i > 7 && !getHighTier(name)) break;

		String voltageName = GTValues.VN[i + 1].toLowerCase();
		machines[i + 1] = registerMetaTileEntity(startId + i, mteCreator.apply(i + 1, voltageName));
								}
						}
}
