package xyz.nepjr.nfmod.common.recipes;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;

public class NFRecipeTypes 
{
	public static void init() {}
	
	public static final GTRecipeType UU_MATTER_FABRICATOR = register("uu_matter_fabricator", ELECTRIC)
            .setMaxIOSize(1,0,1,1)
            .setSlotOverlay(true, true, GuiTextures.FLUID_SLOT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CHEMICAL)
            .setEUIO(IO.IN);
	
	public static final GTRecipeType HIGH_TEMP_STEAM_TURBINE = register("high_temp_steam_turbine", GENERATOR)
			.setMaxIOSize(0, 0, 1, 0)
			.setSlotOverlay(true, true, GuiTextures.FLUID_SLOT)
			.setProgressBar(GuiTextures.PROGRESS_BAR_GAS_COLLECTOR, ProgressTexture.FillDirection.DOWN_TO_UP)
			.setSound(GTSoundEntries.TURBINE)
			.setEUIO(IO.OUT);
}