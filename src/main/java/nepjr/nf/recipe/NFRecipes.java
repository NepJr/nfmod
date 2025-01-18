package nepjr.nf.recipe;

import nepjr.nf.NFTags;
import nepjr.nf.api.util.MaterialUtil;
import nepjr.nf.recipe.removals.NFRecipeRemovals;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = NFTags.MODID)
public class NFRecipes 
{
	@SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) 
    {
		MaterialUtil.buildMaterialRegistry();
		
    	// Removals need to happen first, otherwise bad things might happen
    	NFRecipeRemovals.start();
    			
    	// Now we can actually add recipes
    	NFAssemblerRecipes.start();
    	NFCutterRecipes.start();
    	NFOreHanding.register();
    }
}
