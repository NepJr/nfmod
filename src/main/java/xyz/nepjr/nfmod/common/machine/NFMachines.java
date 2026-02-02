package xyz.nepjr.nfmod.common.machine;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_STEEL_GEARBOX;
import static com.gregtechceu.gtceu.common.data.GTBlocks.CASING_STEEL_TURBINE;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.defaultEnvironmentRequirement;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.defaultTankSizeFunction;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.registerLargeTurbine;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.registerSimpleGenerator;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.registerTieredMachines;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.workableTiered;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTMedicalConditions;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.config.ConfigHolder;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import xyz.nepjr.nfmod.NepFactory;
import xyz.nepjr.nfmod.common.recipes.NFRecipeTypes;

public class NFMachines 
{
	public static final MachineDefinition[] MATTER_FABRICATOR = registerSimpleMachines("matter_fabricator", NFRecipeTypes.UU_MATTER_FABRICATOR, defaultTankSizeFunction, false, "UU-Matter Fabricator", GTValues.tiersBetween(EV, UHV));
	
	// Generators
	public static final MachineDefinition[] HIGH_TEMP_STEAM_TURBINE = registerSimpleGenerator("high_temp_steam_turbine",
            NFRecipeTypes.HIGH_TEMP_STEAM_TURBINE, defaultTankSizeFunction, 0.0f, GTValues.ALL_TIERS);
	
	// Multiblock Generators
	public static final MultiblockMachineDefinition LARGE_HIGH_TEMP_STEAM_TURBINE = registerLargeTurbine("high_temp_steam_large_turbine",
            ZPM,
            NFRecipeTypes.HIGH_TEMP_STEAM_TURBINE,
            CASING_STEEL_TURBINE, CASING_STEEL_GEARBOX,
            GTCEu.id("block/casings/mechanic/machine_casing_turbine_steel"),
            GTCEu.id("block/multiblock/generator/large_steam_turbine"),
            false);
	
	public static void init() {}
	
	// Borrowed from GTCA
	public static MachineDefinition[] registerSimpleMachines(String name,
                                                             GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             boolean hasPollutionDebuff,
                                                             String lang,
                                                             int... tiers) {
    return registerTieredMachines(name,
                (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> {
                    if (hasPollutionDebuff) {
                        builder.recipeModifiers(GTRecipeModifiers.ENVIRONMENT_REQUIREMENT
                                                .apply(GTMedicalConditions.CARBON_MONOXIDE_POISONING, 100 * tier),
                                        GTRecipeModifiers.OC_NON_PERFECT)
                                .conditionalTooltip(defaultEnvironmentRequirement(),
                                        ConfigHolder.INSTANCE.gameplay.environmentalHazards);
                    } else {
                        builder.recipeModifier(GTRecipeModifiers.OC_NON_PERFECT);
                    }
                    return builder
                            .langValue("%s %s %s".formatted(VLVH[tier], lang, VLVT[tier]))
                            .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(NepFactory.id(name), recipeType))
                            .rotationState(RotationState.NON_Y_AXIS)
                            .recipeType(recipeType)
                            .workableTieredHullModel(NepFactory.id("block/machines/" + name))
                            .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType,
                                    tankScalingFunction.apply(tier), true))
                            .register();
                },
                tiers);
    }
}