package nepjr.nfmod.api.unification.material;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;

import static nepjr.nfmod.api.unification.NFMaterials.*;
import static nepjr.nfmod.api.util.NFUtil.nfId;

public class VulcanusMaterials
{
    public static void init()
    {
        Vulcanite = new Material.Builder(4000, nfId("vulcanite"))
                .ingot().fluid().dust()
                .ore()
                .color(0xFF0000).iconSet(MaterialIconSet.METALLIC)
                .build();

        Netherite = new Material.Builder(4001, nfId("netherite"))
                .ingot().fluid().dust()
                .ore()
                .addOreByproducts(Materials.Netherrack)
                .color(0x31292A).iconSet(MaterialIconSet.DULL)
                .build();
    }
}
