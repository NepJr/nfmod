package nepjr.nfmod.common.metatileentity;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Materials;
import nepjr.nfmod.common.metatileentity.machine.MTESpaceMiner;
import nepjr.nfmod.common.metatileentity.multiblock.MTELargeSpaceMiner;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static nepjr.nfmod.api.util.NFUtil.nfId;

public class NFMetaTileEntities
{
    public static MTESpaceMiner SPACE_MINER;

    public static MTELargeSpaceMiner LARGE_SPACE_MINER;

    public static void init()
    {
        // poc
        SPACE_MINER = registerMetaTileEntity(4000, new MTESpaceMiner(nfId("space_ore_drill"), GTValues.UHV, 1, 96, 1));
        LARGE_SPACE_MINER = registerMetaTileEntity(4001, new MTELargeSpaceMiner(nfId("large_space_ore_drill"), GTValues.UHV, 1, 6, 1, Materials.TungstenSteel, 64000));
    }
}
