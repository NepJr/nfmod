package nepjr.nfmod;

import gregtech.GTInternalTags;
import nepjr.nfmod.common.CommonProxy;
import nepjr.nfmod.common.block.NFBlocks;
import nepjr.nfmod.common.cmd.CommandDimTP;
import nepjr.nfmod.common.metatileentity.NFMetaTileEntities;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(   modid = NFTags.MODID,
        version = NFTags.VERSION,
        name = NFTags.MODNAME,
        dependencies = GTInternalTags.DEP_VERSION_STRING,
        acceptedMinecraftVersions = "[1.12.2]")
public class NFMod
{
    public static Logger LOGGER = LogManager.getLogger(NFTags.MODID);

    @SidedProxy(modId = NFTags.MODID,
                clientSide = "nepjr.nfmod.common.ClientProxy",
                serverSide = "nepjr.nfmod.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        NFBlocks.init();
        NFMetaTileEntities.init();
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandDimTP());
    }
}
