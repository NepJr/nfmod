package nepjr.nf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import gregtech.GTInternalTags;
import nepjr.nf.api.util.MaterialUtil;
import nepjr.nf.cmd.CommandDimTP;
import nepjr.nf.cmd.CommandGetProvider;
import nepjr.nf.common.metatileentities.NFMetaTileEntities;
import nepjr.nf.recipe.NFRecipes;
import nepjr.nf.space.dims.NFDimensions;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
		modid = NFTags.MODID, 
		version = NFTags.VERSION, 
		name = NFTags.MODNAME, 
		dependencies = GTInternalTags.DEP_VERSION_STRING, 
		acceptedMinecraftVersions = "[1.12.2]")
public class NepFactory {

    public static final Logger LOGGER = LogManager.getLogger(NFTags.MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	NFMetaTileEntities.init();
    	NFDimensions.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) 
    {
    	
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	
    }
    
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandDimTP());
        event.registerServerCommand(new CommandGetProvider());
    }
    
    @EventHandler
    public void syncConfig(ConfigChangedEvent.OnConfigChangedEvent event)
    {
    	if(event.getModID().equals(NFTags.MODID))
    	{
    		ConfigManager.sync(NFTags.MODID, Type.INSTANCE);
    	}
    }
    
    @NotNull
    public static ResourceLocation nfId(@NotNull String path) {
        return new ResourceLocation(NFTags.MODID, path);
    }
}
