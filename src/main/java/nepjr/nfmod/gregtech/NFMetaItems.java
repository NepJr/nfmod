package nepjr.nfmod.gregtech;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;

public class NFMetaItems {
	
	private static StandardMetaItem metaItems;
	
	public static void initMetaItems()
	{
		metaItems = new StandardMetaItem();
		metaItems.setRegistryName("nf_meta_item");
	}
	
	public static MetaItem<?>.MetaValueItem TEST;
	
	// Circuits
    // Gooware
    public static MetaItem<?>.MetaValueItem GOOWARE_PROCESSOR;
    public static MetaItem<?>.MetaValueItem GOOWARE_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem GOOWARE_COMPUTER;
    public static MetaItem<?>.MetaValueItem GOOWARE_MAINFRAME;

    // Optical
    public static MetaItem<?>.MetaValueItem OPTICAL_PROCESSOR;
    public static MetaItem<?>.MetaValueItem OPTICAL_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem OPTICAL_COMPUTER;
    public static MetaItem<?>.MetaValueItem OPTICAL_MAINFRAME;

    // Spintronic
    public static MetaItem<?>.MetaValueItem SPINTRONIC_PROCESSOR;
    public static MetaItem<?>.MetaValueItem SPINTRONIC_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem SPINTRONIC_COMPUTER;
    public static MetaItem<?>.MetaValueItem SPINTRONIC_MAINFRAME;

    // Cosmic, name TBD
    public static MetaItem<?>.MetaValueItem COSMIC_PROCESSOR;
    public static MetaItem<?>.MetaValueItem COSMIC_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem COSMIC_COMPUTER;
    public static MetaItem<?>.MetaValueItem COSMIC_MAINFRAME;

    // Supra-causal
    public static MetaItem<?>.MetaValueItem SUPRACAUSAL_PROCESSOR;
    public static MetaItem<?>.MetaValueItem SUPRACAUSAL_ASSEMBLY;
    public static MetaItem<?>.MetaValueItem SUPRACAUSAL_COMPUTER;
    public static MetaItem<?>.MetaValueItem SUPRACAUSAL_MAINFRAME;

    // Supra-chronal
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_ULV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_LV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_MV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_HV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_EV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_IV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_LuV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_ZPM;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_UV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_UHV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_UEV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_UIV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_UXV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_OpV;
    public static MetaItem<?>.MetaValueItem SUPRACHRONAL_MAX;
	
	public static void init()
	{
		registerSubItems();
	}
	
	private static void registerSubItems()
	{
		// Circuits: ID 0-49
        GOOWARE_PROCESSOR = metaItems.addItem(0, "circuit.gooware_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.ZPM);
        GOOWARE_ASSEMBLY = metaItems.addItem(1, "circuit.gooware_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV);
        GOOWARE_COMPUTER = metaItems.addItem(2, "circuit.gooware_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV);
        GOOWARE_MAINFRAME = metaItems.addItem(3, "circuit.gooware_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV);

        OPTICAL_PROCESSOR = metaItems.addItem(4,"circuit.optical_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV);
        OPTICAL_ASSEMBLY = metaItems.addItem(5,"circuit.optical_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV);
        OPTICAL_COMPUTER = metaItems.addItem(6,"circuit.optical_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV);
        OPTICAL_MAINFRAME = metaItems.addItem(7,"circuit.optical_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV);

        SPINTRONIC_PROCESSOR = metaItems.addItem(8, "circuit.spintronic_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV);
        SPINTRONIC_ASSEMBLY = metaItems.addItem(9, "circuit.spintronic_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV);
        SPINTRONIC_COMPUTER = metaItems.addItem(10, "circuit.spintronic_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV);
        SPINTRONIC_MAINFRAME = metaItems.addItem(11, "circuit.spintronic_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV);

        COSMIC_PROCESSOR = metaItems.addItem(12, "circuit.cosmic_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV);
        COSMIC_ASSEMBLY = metaItems.addItem(13, "circuit.cosmic_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV);
        COSMIC_COMPUTER = metaItems.addItem(14, "circuit.cosmic_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV);
        COSMIC_MAINFRAME = metaItems.addItem(15, "circuit.cosmic_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV);

        SUPRACAUSAL_PROCESSOR = metaItems.addItem(16, "circuit.supracausal_processor").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV);
        SUPRACAUSAL_ASSEMBLY = metaItems.addItem(17, "circuit.supracausal_assembly").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV);
        SUPRACAUSAL_COMPUTER = metaItems.addItem(18, "circuit.supracausal_computer").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV);
        SUPRACAUSAL_MAINFRAME = metaItems.addItem(19, "circuit.supracausal_mainframe").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MAX);

        SUPRACHRONAL_ULV = metaItems.addItem(20, "circuit.suprachronal.ulv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.ULV);
        SUPRACHRONAL_LV = metaItems.addItem(21, "circuit.suprachronal.lv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.LV);
        SUPRACHRONAL_MV = metaItems.addItem(22, "circuit.suprachronal.mv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MV);
        SUPRACHRONAL_HV = metaItems.addItem(23, "circuit.suprachronal.hv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.HV);
        SUPRACHRONAL_EV = metaItems.addItem(24, "circuit.suprachronal.ev").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.EV);
        SUPRACHRONAL_IV = metaItems.addItem(25, "circuit.suprachronal.iv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.IV);
        SUPRACHRONAL_LuV = metaItems.addItem(26, "circuit.suprachronal.luv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.LuV);
        SUPRACHRONAL_ZPM = metaItems.addItem(27, "circuit.suprachronal.zpm").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.ZPM);
        SUPRACHRONAL_UV = metaItems.addItem(28, "circuit.suprachronal.uv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV);
        SUPRACHRONAL_UHV = metaItems.addItem(29, "circuit.suprachronal.uhv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV);
        SUPRACHRONAL_UEV = metaItems.addItem(30, "circuit.suprachronal.uev").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV);
        SUPRACHRONAL_UIV = metaItems.addItem(31, "circuit.suprachronal.uiv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV);
        SUPRACHRONAL_UXV = metaItems.addItem(32, "circuit.suprachronal.uxv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV);
        SUPRACHRONAL_OpV = metaItems.addItem(33, "circuit.suprachronal.opv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV);
        SUPRACHRONAL_MAX = metaItems.addItem(34, "circuit.suprachronal.max").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MAX);
	}
}
