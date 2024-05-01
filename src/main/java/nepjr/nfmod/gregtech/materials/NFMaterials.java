package nepjr.nfmod.gregtech.materials;

import gregtech.api.unification.Elements;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.event.MaterialEvent;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.WireProperties;
import nepjr.nfmod.NFTags;
import nepjr.nfmod.gregtech.NFElements;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static gregtech.api.unification.material.Materials.Hydrogen;
import static gregtech.api.unification.material.Materials.Oxygen;
import static gregtech.api.unification.material.Materials.Sulfur;
import static gregtech.api.util.GTUtility.gregtechId;

import gregtech.api.GTValues;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.attribute.FluidAttributes;

@Mod.EventBusSubscriber(modid = NFTags.MODID)
public class NFMaterials 
{
	public static Material originitePrime;
	public static Material originium;
	public static Material orundum;
	public static Material sulfuricOriginium;
	
	public static Material chimite;
	
	public static Material blazingPyrotheum;
	public static Material gelidCryotheum;
	
	public static Material cryonite;
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void registerMaterials(MaterialEvent event)
	{
		NFMaterials.originitePrime = new Material.Builder(5000, gregtechId("originite_prime"))
				.color(0xFFAE42)
				.iconSet(MaterialIconSet.DIAMOND)
				.element(NFElements.Or)
				.dust()
				.gem()
				.build();
		
		NFMaterials.originium = new Material.Builder(5001, gregtechId("originium"))
				.color(0x000000)
				.element(NFElements.Or)
				.ingot()
				.fluid()
				.cableProperties(GTValues.V[GTValues.UEV], 4, 4)
				.ore()
				.build();
		
		NFMaterials.orundum = new Material.Builder(5002, gregtechId("orundum"))
				.color(0xBF0000)
				.iconSet(MaterialIconSet.METALLIC)
				.element(NFElements.Or)
				.ingot().fluid().dust()
				.flags(MaterialFlags.GENERATE_PLATE)
				.build();
		
		NFMaterials.sulfuricOriginium = new Material.Builder(5003, gregtechId("sulfuric_originium_ore"))
				.color(0x000000)
				.components(Hydrogen, 2, Sulfur, 1, Oxygen, 4, NFMaterials.originium, 1)
				.flags(MaterialFlags.DISABLE_DECOMPOSITION)
				.liquid(new FluidBuilder()
                        .attribute(FluidAttributes.ACID))
				.build();
		
		NFMaterials.blazingPyrotheum = new Material.Builder(5004, gregtechId("blazing_pyrotheum"))
				.color(0xFF8800)
				.liquid(new FluidBuilder()
                        .attribute(FluidAttributes.ACID))
				.flags(MaterialFlags.DISABLE_DECOMPOSITION)
				.build();
		
		NFMaterials.gelidCryotheum = new Material.Builder(5005, gregtechId("gelid_cryotheum"))
				.color(0xADD8E6)
				.fluid()
				.flags(MaterialFlags.DISABLE_DECOMPOSITION)
				.build();
		
		NFMaterials.cryonite = new Material.Builder(5006, gregtechId("cryonite"))
				.color(0xADD8FF)
				.fluid().ingot().dust()
				.iconSet(MaterialIconSet.SHINY)
				.cableProperties(2147483647, 8, 0, true)
				.build();
		
		NFMaterials.chimite = new Material.Builder(5007, gregtechId("chimite"))
				.color(0xFF5300)
				.gem()
				.fluid()
				.iconSet(MaterialIconSet.RUBY)
				.build();
		
		// Modify existing materials here
		Materials.Neutronium.addFlags(MaterialFlags.GENERATE_FINE_WIRE, MaterialFlags.GENERATE_ROUND, MaterialFlags.GENERATE_RING);
		
		Materials.Neutronium.setProperty(PropertyKey.WIRE, new WireProperties((int) GTValues.V[GTValues.UHV], 8, 1, false));
		
		Materials.Tritanium.addFlags(MaterialFlags.GENERATE_ROTOR);
		
		Materials.Europium.getProperties().getProperty(PropertyKey.WIRE).setAmperage(4);
		Materials.Europium.getProperties().getProperty(PropertyKey.WIRE).setLossPerBlock(4);
	}
}
