package nepjr.nf.api.metatileentity.multiblock;

import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import nepjr.nf.api.capability.ISolarPanel;

public class NFMultiblockAbility
{
	public static final MultiblockAbility<ISolarPanel> SOLAR_PANEL = new MultiblockAbility<>("solar_panel");
	
	private NFMultiblockAbility() {}
}
