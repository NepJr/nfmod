package nepjr.nf.api.unification;

import static nepjr.nf.NepFactory.nfId;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.info.MaterialIconSet;

public class NepUnsortedMaterials 
{
	public static void init()
	{
		NepMaterials.Mostimite = new Material.Builder(10000, nfId("mostimite"))
				.ingot().fluid()
				.flags(MaterialFlags.GENERATE_PLATE)
				.color(0x0000FF).iconSet(MaterialIconSet.METALLIC)
				.build();
		
		NepMaterials.Fiammettite = new Material.Builder(10001, nfId("fiammettite"))
				.ingot().fluid()
				.flags(MaterialFlags.GENERATE_PLATE)
				.color(0xCB2127).iconSet(MaterialIconSet.METALLIC)
				.build();
	}
}
