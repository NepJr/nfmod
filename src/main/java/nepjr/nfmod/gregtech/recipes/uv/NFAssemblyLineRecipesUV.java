package nepjr.nfmod.gregtech.recipes.uv;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLY_LINE_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;

public class NFAssemblyLineRecipesUV 
{
	public static void begin()
	{
		ASSEMBLY_LINE_RECIPES.recipeBuilder()
        .input(stickLong, SamariumMagnetic)
        .input(stickLong, Tritanium, 4)
        .input(ring, Tritanium, 4)
        .input(round, Tritanium, 8)
        .input(wireFine, Neutronium, 64)
        .input(wireFine, Neutronium, 64)
        .input(cableGtSingle, Europium, 2)
        .fluidInputs(SolderingAlloy.getFluid(L * 4))
        .fluidInputs(Lubricant.getFluid(1000))
        .fluidInputs(Neutronium.getFluid(L * 4))
        .output(ELECTRIC_MOTOR_UHV)
        .stationResearch(b -> b
                .researchStack(ELECTRIC_MOTOR_UV.getStackForm())
                .CWUt(128)
                .EUt(VA[UV]))
        .duration(600).EUt(480000).buildAndRegister();
		
		ASSEMBLY_LINE_RECIPES.recipeBuilder()
        .input(ELECTRIC_MOTOR_UHV, 2)
        .input(plate, Neutronium, 2)
        .input(ring, Neutronium, 4)
        .input(round, Neutronium, 16)
        .input(screw, Neutronium, 4)
        .input(cableGtSingle, Europium, 2)
        .fluidInputs(SolderingAlloy.getFluid(L * 4))
        .fluidInputs(Lubricant.getFluid(1000))
        .fluidInputs(StyreneButadieneRubber.getFluid(L * 24))
        .fluidInputs(Neutronium.getFluid(L * 4))
        .output(CONVEYOR_MODULE_UHV)
        .stationResearch(b -> b
                .researchStack(CONVEYOR_MODULE_UV.getStackForm())
                .CWUt(128)
                .EUt(VA[ZPM]))
        .duration(600).EUt(480000).buildAndRegister();
	}
}
