package nepjr.nfmod.common;

import nepjr.nfmod.NFTags;
import nepjr.nfmod.common.block.NFBlocks;
import nepjr.nfmod.common.cmd.CommandDimTP;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = NFTags.MODID)
public class CommonProxy
{
    public void preLoad()
    {}
}
