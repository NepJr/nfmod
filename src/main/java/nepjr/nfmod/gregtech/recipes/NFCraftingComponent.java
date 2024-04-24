package nepjr.nfmod.gregtech.recipes;

import static gregtech.api.GTValues.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.loaders.recipe.CraftingComponent;
import gregtech.loaders.recipe.CraftingComponent.Component;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class NFCraftingComponent extends CraftingComponent 
{
	public static void initializeComponents()
	{
	
	WIRE_QUAD = new Component(Stream.of(new Object[][] {

            { 0, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Lead) },
            { 1, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Tin) },
            { 2, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Copper) },
            { 3, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Gold) },
            { 4, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Aluminium) },
            { 5, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Tungsten) },
            { 6, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.NiobiumTitanium) },
            { 7, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.VanadiumGallium) },
            { 8, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.YttriumBariumCuprate) },
            { 9, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Europium) },

    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

    WIRE_OCT = new Component(Stream.of(new Object[][] {

            { 0, new UnificationEntry(OrePrefix.wireGtOctal, Materials.Lead) },
            { 1, new UnificationEntry(OrePrefix.wireGtOctal, Materials.Tin) },
            { 2, new UnificationEntry(OrePrefix.wireGtOctal, Materials.Copper) },
            { 3, new UnificationEntry(OrePrefix.wireGtOctal, Materials.Gold) },
            { 4, new UnificationEntry(OrePrefix.wireGtOctal, Materials.Aluminium) },
            { 5, new UnificationEntry(OrePrefix.wireGtOctal, Materials.Tungsten) },
            { 6, new UnificationEntry(OrePrefix.wireGtOctal, Materials.NiobiumTitanium) },
            { 7, new UnificationEntry(OrePrefix.wireGtOctal, Materials.VanadiumGallium) },
            { 8, new UnificationEntry(OrePrefix.wireGtOctal, Materials.YttriumBariumCuprate) },
            { 9, new UnificationEntry(OrePrefix.wireGtOctal, Materials.Europium) },

    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

    WIRE_HEX = new Component(Stream.of(new Object[][] {

            { 0, new UnificationEntry(OrePrefix.wireGtHex, Materials.Lead) },
            { 1, new UnificationEntry(OrePrefix.wireGtHex, Materials.Tin) },
            { 2, new UnificationEntry(OrePrefix.wireGtHex, Materials.Copper) },
            { 3, new UnificationEntry(OrePrefix.wireGtHex, Materials.Gold) },
            { 4, new UnificationEntry(OrePrefix.wireGtHex, Materials.Aluminium) },
            { 5, new UnificationEntry(OrePrefix.wireGtHex, Materials.Tungsten) },
            { 6, new UnificationEntry(OrePrefix.wireGtHex, Materials.NiobiumTitanium) },
            { 7, new UnificationEntry(OrePrefix.wireGtHex, Materials.VanadiumGallium) },
            { 8, new UnificationEntry(OrePrefix.wireGtHex, Materials.YttriumBariumCuprate) },
            { 9, new UnificationEntry(OrePrefix.wireGtHex, Materials.Europium) },

    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
		
	COIL_HEATING = new Component(Stream.of(new Object[][] {

            { 0, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Copper) },
            { 1, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Copper) },
            { 2, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Cupronickel) },
            { 3, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Kanthal) },
            { 4, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Nichrome) },
            { 5, new UnificationEntry(OrePrefix.wireGtDouble, Materials.RTMAlloy) },
            { 6, new UnificationEntry(OrePrefix.wireGtDouble, Materials.HSSG) },
            { 7, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Naquadah) },
            { 8, new UnificationEntry(OrePrefix.wireGtDouble, Materials.NaquadahAlloy) },
            { 9, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Neutronium) },

    }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
	
	COIL_HEATING_DOUBLE = new Component(Stream.of(new Object[][] {

        	{ 0, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Copper) },
        	{ 1, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Copper) },
        	{ 2, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Cupronickel) },
        	{ 3, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Kanthal) },
        	{ 4, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Nichrome) },
        	{ 5, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.RTMAlloy) },
        	{ 6, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.HSSG) },
        	{ 7, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Naquadah) },
        	{ 8, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.NaquadahAlloy) },
        	{ 9, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Neutronium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
	
	ROTOR = new Component(Stream.of(new Object[][] {

        	{ 0, new UnificationEntry(OrePrefix.rotor, Materials.Tin) },
        	{ 1, new UnificationEntry(OrePrefix.rotor, Materials.Tin) },
        	{ 2, new UnificationEntry(OrePrefix.rotor, Materials.Bronze) },
        	{ 3, new UnificationEntry(OrePrefix.rotor, Materials.Steel) },
        	{ 4, new UnificationEntry(OrePrefix.rotor, Materials.StainlessSteel) },
        	{ 5, new UnificationEntry(OrePrefix.rotor, Materials.TungstenSteel) },
        	{ 6, new UnificationEntry(OrePrefix.rotor, Materials.RhodiumPlatedPalladium) },
        	{ 7, new UnificationEntry(OrePrefix.rotor, Materials.NaquadahAlloy) },
        	{ 8, new UnificationEntry(OrePrefix.rotor, Materials.Darmstadtium) },
        	{ 9, new UnificationEntry(OrePrefix.rotor, Materials.Tritanium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
	
	PIPE_NORMAL = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.Bronze) },
        { 1, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.Bronze) },
        { 2, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.Steel) },
        { 3, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.StainlessSteel) },
        { 4, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.Titanium) },
        { 5, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.TungstenSteel) },
        { 6, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.NiobiumTitanium) },
        { 7, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.Iridium) },
        { 8, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.Naquadah) },
        { 9, new UnificationEntry(OrePrefix.pipeNormalFluid, Materials.Neutronium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

	PIPE_LARGE = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.Bronze) },
        { 1, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.Bronze) },
        { 2, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.Steel) },
        { 3, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.StainlessSteel) },
        { 4, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.Titanium) },
        { 5, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.TungstenSteel) },
        { 6, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.NiobiumTitanium) },
        { 7, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.Ultimet) },
        { 8, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.Naquadah) },
        { 9, new UnificationEntry(OrePrefix.pipeLargeFluid, Materials.Neutronium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
	
	WIRE_ELECTRIC = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Gold) },
        { 1, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Gold) },
        { 2, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Silver) },
        { 3, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Electrum) },
        { 4, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Platinum) },
        { 5, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Osmium) },
        { 6, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Osmium) },
        { 7, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Osmium) },
        { 8, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Osmium) },
        { 9, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Europium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
	
	CABLE = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.cableGtSingle, Materials.RedAlloy) },
        { 1, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin) },
        { 2, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper) },
        { 3, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold) },
        { 4, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium) },
        { 5, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Platinum) },
        { 6, new UnificationEntry(OrePrefix.cableGtSingle, Materials.NiobiumTitanium) },
        { 7, new UnificationEntry(OrePrefix.cableGtSingle, Materials.VanadiumGallium) },
        { 8, new UnificationEntry(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate) },
        { 9, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Europium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

	CABLE_QUAD = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.RedAlloy) },
        { 1, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Tin) },
        { 2, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Copper) },
        { 3, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Gold) },
        { 4, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Aluminium) },
        { 5, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Platinum) },
        { 6, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium) },
        { 7, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium) },
        { 8, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.YttriumBariumCuprate) },
        { 9, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Europium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

	CABLE_OCT = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.cableGtOctal, Materials.RedAlloy) },
        { 1, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Tin) },
        { 2, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Copper) },
        { 3, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Gold) },
        { 4, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Aluminium) },
        { 5, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Platinum) },
        { 6, new UnificationEntry(OrePrefix.cableGtOctal, Materials.NiobiumTitanium) },
        { 7, new UnificationEntry(OrePrefix.cableGtOctal, Materials.VanadiumGallium) },
        { 8, new UnificationEntry(OrePrefix.cableGtOctal, Materials.YttriumBariumCuprate) },
        { 9, new UnificationEntry(OrePrefix.cableGtOctal, Materials.Europium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

	CABLE_HEX = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.cableGtHex, Materials.RedAlloy) },
        { 1, new UnificationEntry(OrePrefix.cableGtHex, Materials.Tin) },
        { 2, new UnificationEntry(OrePrefix.cableGtHex, Materials.Copper) },
        { 3, new UnificationEntry(OrePrefix.cableGtHex, Materials.Gold) },
        { 4, new UnificationEntry(OrePrefix.cableGtHex, Materials.Aluminium) },
        { 5, new UnificationEntry(OrePrefix.cableGtHex, Materials.Platinum) },
        { 6, new UnificationEntry(OrePrefix.cableGtHex, Materials.NiobiumTitanium) },
        { 7, new UnificationEntry(OrePrefix.cableGtHex, Materials.VanadiumGallium) },
        { 8, new UnificationEntry(OrePrefix.cableGtHex, Materials.YttriumBariumCuprate) },
        { 9, new UnificationEntry(OrePrefix.cableGtHex, Materials.Europium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

	CABLE_TIER_UP = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Tin) },
        { 1, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Copper) },
        { 2, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Gold) },
        { 3, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Aluminium) },
        { 4, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Platinum) },
        { 5, new UnificationEntry(OrePrefix.cableGtSingle, Materials.NiobiumTitanium) },
        { 6, new UnificationEntry(OrePrefix.cableGtSingle, Materials.VanadiumGallium) },
        { 7, new UnificationEntry(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate) },
        { 8, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Europium) },
        { GTValues.FALLBACK, new UnificationEntry(OrePrefix.cableGtSingle, Materials.Europium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

	CABLE_QUAD_TIER_UP = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Tin) },
        { 1, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Copper) },
        { 2, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Gold) },
        { 3, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Aluminium) },
        { 4, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Platinum) },
        { 5, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium) },
        { 6, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium) },
        { 7, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.YttriumBariumCuprate) },
        { 8, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Europium) },
        { GTValues.FALLBACK, new UnificationEntry(OrePrefix.cableGtQuadruple, Materials.Europium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
	
	COIL_ELECTRIC = new Component(Stream.of(new Object[][] {

        { 0, new UnificationEntry(OrePrefix.wireGtSingle, Materials.Tin) },
        { 1, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Tin) },
        { 2, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Copper) },
        { 3, new UnificationEntry(OrePrefix.wireGtDouble, Materials.Silver) },
        { 4, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Steel) },
        { 5, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.Graphene) },
        { 6, new UnificationEntry(OrePrefix.wireGtQuadruple, Materials.NiobiumNitride) },
        { 7, new UnificationEntry(OrePrefix.wireGtOctal, Materials.VanadiumGallium) },
        { 8, new UnificationEntry(OrePrefix.wireGtOctal, Materials.YttriumBariumCuprate) },
        { 9, new UnificationEntry(OrePrefix.wireGtOctal, Materials.Europium) },

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
	
	GLASS = new Component(Stream.of(new Object[][] {

        	{ GTValues.FALLBACK, new ItemStack(Blocks.GLASS, 1, GTValues.W) },
        	{ ULV, Blocks.GLASS },
        	{ LV, Blocks.GLASS },
        	{ MV, Blocks.GLASS },
        	{ HV, MetaBlocks.TRANSPARENT_CASING.getItemVariant(
        			BlockGlassCasing.CasingType.TEMPERED_GLASS) },
        	{ EV, MetaBlocks.TRANSPARENT_CASING.getItemVariant(
        			BlockGlassCasing.CasingType.TEMPERED_GLASS) },
        	{ IV, MetaBlocks.TRANSPARENT_CASING.getItemVariant(
        			BlockGlassCasing.CasingType.LAMINATED_GLASS) },
        	{ LuV, MetaBlocks.TRANSPARENT_CASING.getItemVariant(
        			BlockGlassCasing.CasingType.LAMINATED_GLASS) },
        	{ ZPM, MetaBlocks.TRANSPARENT_CASING.getItemVariant(
        			BlockGlassCasing.CasingType.FUSION_GLASS) },
        	{ UV, MetaBlocks.TRANSPARENT_CASING.getItemVariant(
        			BlockGlassCasing.CasingType.FUSION_GLASS) },
        	{ UHV, MetaBlocks.TRANSPARENT_CASING.getItemVariant(
        			BlockGlassCasing.CasingType.FUSION_GLASS) }
        			

	}).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
	
	}
	
	
}
