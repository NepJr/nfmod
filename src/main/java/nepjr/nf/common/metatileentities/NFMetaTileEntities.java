package nepjr.nf.common.metatileentities;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static nepjr.nf.NepFactory.nfId;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import nepjr.nf.common.metatileentities.generator.MetaTileEntitySolarPanel;
import nepjr.nf.common.metatileentities.multiblock.generator.MetaTileEntitySolarArray;
import nepjr.nf.common.metatileentities.multiblock.normal.MetaTileEntityTestMultiblock;
import nepjr.nf.common.metatileentities.multiblock.part.MetaTileEntitySolarPanelPart;

public class NFMetaTileEntities
{
	public static MetaTileEntityTestMultiblock TEST;
	
	/**
	 * Solar Array multiblock exists, much better to just use that honestly 
	 */
	@Deprecated
	public static MetaTileEntitySolarPanel SOLAR_PANEL;
	
	public static MetaTileEntitySolarArray[] SOLAR_ARRAY = new MetaTileEntitySolarArray[GTValues.V.length - 1];
	
	public static MetaTileEntitySolarPanelPart[] SOLAR_PANEL_PART = new MetaTileEntitySolarPanelPart[GTValues.V.length - 1];
	
	public static void init()
	{
		TEST = registerMetaTileEntity(4000, new MetaTileEntityTestMultiblock(nfId("test_multi")));
		
		SOLAR_PANEL = registerMetaTileEntity(4001, new MetaTileEntitySolarPanel(nfId("solar_panel.uv"), GTValues.UV));
		
		// Solar Array and Solar Panel Parts
		int endPos = GregTechAPI.isHighTier() ? SOLAR_PANEL_PART.length : Math.min(SOLAR_PANEL_PART.length - 1, GTValues.UV + 2);
        for (int i = 0; i < endPos; i++) {
        	SOLAR_PANEL_PART[i] = new MetaTileEntitySolarPanelPart(nfId("solar_panel_part." + GTValues.VN[i].toLowerCase()), i);
        	SOLAR_ARRAY[i] = new MetaTileEntitySolarArray(nfId("solar_array_" + GTValues.VN[i].toLowerCase()), i);
            registerMetaTileEntity(4100 + i, SOLAR_PANEL_PART[i]);
            registerMetaTileEntity(4120 + i, SOLAR_ARRAY[i]);
        }
	}
}
