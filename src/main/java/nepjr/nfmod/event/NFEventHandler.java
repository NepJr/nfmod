package nepjr.nfmod.event;

import gregtech.api.GregTechAPI;
import gregtech.api.event.HighTierEvent;
import gregtech.api.recipes.GTRecipeInputCache;
import gregtech.loaders.recipe.CraftingComponent;
import nepjr.nfmod.NFTags;
import nepjr.nfmod.gregtech.recipes.NFCraftingComponent;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = NFTags.MODID)
public class NFEventHandler 
{
	@SubscribeEvent
	static void enableHighTier(HighTierEvent event)
	{
		event.enableHighTier();
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void initComponents(RegistryEvent.Register<IRecipe> event) {
        NFCraftingComponent.initializeComponents();
        MinecraftForge.EVENT_BUS.post(new GregTechAPI.RegisterEvent<>(null, NFCraftingComponent.class));
    }
}
