package nepjr.nfmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nepjr.nfmod.gregtech.metatileentities.NFMetaTileEntities;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = NFTags.MODID, 
	version = NFTags.VERSION, 
	name = NFTags.MODNAME, 
	acceptedMinecraftVersions = "[1.12.2]",
	dependencies = "required-after:gregtech@[2.8,);" 
				 + "required-after:gcym@[1.2.8,);")
public class NFMod 
{
	public static final Logger LOGGER = LogManager.getLogger(NFTags.MODID);
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		NFMetaTileEntities.init();
	}
}
