package nepjr.nf.event;

import gregtech.api.event.HighTierEvent;
import gregtech.api.unification.material.event.MaterialEvent;
import nepjr.nf.NFTags;
import nepjr.nf.api.unification.NepMaterials;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NFTags.MODID)
public class NepEvents {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void registerMaterials(MaterialEvent event)
	{
		NepMaterials.init();
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
    public static void enableHighTier(HighTierEvent event)
    {
    	event.enableHighTier();
    }
	
	@SubscribeEvent
	public static void otherPlanetGravity(LivingUpdateEvent event)
	{
		// This will be done last as this will probably be the most complex thing to do
	}
	
}
