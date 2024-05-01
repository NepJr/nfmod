package nepjr.nfmod.gregtech.metatileentities;

import static gregtech.api.util.GTUtility.gregtechId;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import gregtech.api.util.Mods;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityEnergyHatch;
import gregtech.integration.jei.multiblock.MultiblockInfoCategory;
import nepjr.nfmod.gregtech.machines.AdvancedAL;
import nepjr.nfmod.gregtech.machines.AdvancedLCR;
import nepjr.nfmod.gregtech.machines.OreCrusher;
import nepjr.nfmod.gregtech.machines.TimeManipulationDevice;
import net.minecraft.util.ResourceLocation;

public class NFMetaTileEntities 
{
	
	// Used for addons if they wish to disable certain tiers of machines
    private static final Map<String, Boolean> MID_TIER = new HashMap<>();
    private static final Map<String, Boolean> HIGH_TIER = new HashMap<>();
    
    
    // Multiblock Parts
    public static final MetaTileEntityEnergyHatch[] MAX_INPUT_HATCH = new MetaTileEntityEnergyHatch[GTValues.V.length];
    public static final MetaTileEntityEnergyHatch[] MAX_OUTPUT_HATCH = new MetaTileEntityEnergyHatch[GTValues.V.length];
    
    
    // Multiblocks
    public static AdvancedLCR ADVANCED_LCR;
    public static AdvancedAL ADVANCED_AL;
    public static OreCrusher ORE_CRUSHER;
    public static TimeManipulationDevice timeMachine;
    
    public static void init()
    {
    	MAX_INPUT_HATCH[14] = registerMetaTileEntity(4000,
                new MetaTileEntityEnergyHatch(gregtechId("energy_hatch.input." + GTValues.VN[14].toLowerCase()), 14, 2, false));
    
    	MAX_OUTPUT_HATCH[14] = registerMetaTileEntity(4001,
                new MetaTileEntityEnergyHatch(gregtechId("energy_hatch.output." + GTValues.VN[14].toLowerCase()), 14, 2, true));

    	ADVANCED_LCR = registerMetaTileEntity(5000,
                new AdvancedLCR(gregtechId("advanced_lcr")));
    	
    	ADVANCED_AL = registerMetaTileEntity(5001,
                new AdvancedAL(gregtechId("advanced_al")));
    	
    	ORE_CRUSHER = registerMetaTileEntity(5002,
    			new OreCrusher(gregtechId("ore_crusher")));
    	
    	timeMachine = registerMetaTileEntity(5003,
    			new TimeManipulationDevice(gregtechId("time_machine")));

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

    @SuppressWarnings("unused")
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

    public static <T extends MetaTileEntity> T registerMetaTileEntity(int id, T sampleMetaTileEntity) {
        if (sampleMetaTileEntity instanceof IMultiblockAbilityPart abilityPart) {
            MultiblockAbility.registerMultiblockAbility(abilityPart.getAbility(), sampleMetaTileEntity);
        }
        if (sampleMetaTileEntity instanceof MultiblockControllerBase && Mods.JustEnoughItems.isModLoaded()) {
            if (((MultiblockControllerBase) sampleMetaTileEntity).shouldShowInJei()) {
                MultiblockInfoCategory.registerMultiblock((MultiblockControllerBase) sampleMetaTileEntity);
            }
        }
        GregTechAPI.MTE_REGISTRY.register(id, sampleMetaTileEntity.metaTileEntityId, sampleMetaTileEntity);
        return sampleMetaTileEntity;
    }

    @SuppressWarnings("unused")
    public static void setMidTier(String key, boolean enabled) {
        MID_TIER.put(key, enabled);
    }

    @SuppressWarnings("unused")
    public static void setHighTier(String key, boolean enabled) {
        HIGH_TIER.put(key, enabled);
        if (!GregTechAPI.isHighTier()) {
            throw new IllegalArgumentException(
                    "Cannot set High-Tier machine without high tier being enabled in GregTechAPI.");
        }
    }

    public static boolean getMidTier(String key) {
        return MID_TIER.getOrDefault(key, true);
    }

    public static boolean getHighTier(String key) {
        return HIGH_TIER.getOrDefault(key, GregTechAPI.isHighTier());
    }
}
