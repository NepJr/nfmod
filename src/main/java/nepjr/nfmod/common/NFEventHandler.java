package nepjr.nfmod.common;

import gregtech.api.unification.material.event.MaterialEvent;
import nepjr.nfmod.NFTags;
import nepjr.nfmod.api.unification.NFMaterials;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = NFTags.MODID)
public class NFEventHandler
{
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(MaterialEvent event)
    {
        NFMaterials.init();
    }
}
