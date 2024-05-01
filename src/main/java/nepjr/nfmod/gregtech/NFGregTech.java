package nepjr.nfmod.gregtech;

import java.util.Objects;
import java.util.function.Function;

import gregtech.api.GregTechAPI;
import gregtech.api.block.VariantItemBlock;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import nepjr.nfmod.NFMod;
import nepjr.nfmod.NFTags;
import nepjr.nfmod.gregtech.blocks.NFMetaBlocks;
import nepjr.nfmod.gregtech.metatileentities.NFMetaTileEntities;
import nepjr.nfmod.gregtech.recipes.NFRecipeGenerator;
import nepjr.nfmod.gregtech.recipes.NFRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import shadows.singularity.item.Singularity;

@Mod.EventBusSubscriber(modid = NFTags.MODID)
public class NFGregTech 
{
	public static void preInit(FMLPreInitializationEvent event)
	{
		NFMetaTileEntities.init();
		NFMetaBlocks.init();
		NFMetaItems.initMetaItems();
		
		for(Material m : GregTechAPI.materialManager.getRegisteredMaterials())
		{
			if(m.getName() != "infinity" && (m.hasProperty(PropertyKey.INGOT) || m.hasProperty(PropertyKey.GEM)))
			{	
				Singularity.register(new Singularity(m.getLocalizedName(), m.getMaterialRGB(), 0x000000, m.getId(), EnumRarity.EPIC, true));
			}
		}
	}
	
	public static void init(FMLInitializationEvent event)
	{
		NFRecipes.initRecipes();
	}
	
	@SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) 
    {
        NFMod.LOGGER.info("Registering Blocks...");
        IForgeRegistry<Block> registry = event.getRegistry();
        
        registry.register(NFMetaBlocks.nfMachineCasings);
    }
	
	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
		// NFMetaItems.init();
		
		NFMod.LOGGER.info("Registering Items...");
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(createItemBlock(NFMetaBlocks.nfMachineCasings, VariantItemBlock::new));
    }

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        itemBlock.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
        return itemBlock;
    }
    
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
    	NFRecipeGenerator.init();
    }
}
