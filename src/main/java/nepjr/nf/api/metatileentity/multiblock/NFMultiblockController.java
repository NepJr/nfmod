package nepjr.nf.api.metatileentity.multiblock;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.utils.TooltipHelper;
import nepjr.nf.cfg.NFConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class NFMultiblockController extends MultiMapMultiblockController{

	public NFMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
		this(metaTileEntityId, new RecipeMap<?>[] { recipeMap });
	}
	
	public NFMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?>[] recipeMaps) {
		super(metaTileEntityId, recipeMaps);
		this.recipeMapWorkable = new MultiblockRecipeLogic(this, true);
		this.recipeMapWorkable.setParallelLimit(NFConfig.machineOptions.parallelLimit);
	}
	
	@Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(TooltipHelper.BLINKING_CYAN + I18n.format("nf.tooltip.max_parallel") + " " + NFConfig.machineOptions.parallelLimit);
        tooltip.add(TooltipHelper.RAINBOW + I18n.format("gregtech.machine.perfect_oc"));
    }

}
