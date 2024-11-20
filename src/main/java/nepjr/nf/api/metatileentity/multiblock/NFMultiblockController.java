package nepjr.nf.api.metatileentity.multiblock;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.ParallelLogicType;
import gregtech.api.recipes.RecipeMap;
import gregtech.client.utils.TooltipHelper;
import nepjr.nf.api.data.EnumMultiTier;
import nepjr.nf.cfg.NFConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class NFMultiblockController extends MultiMapMultiblockController {

	private EnumMultiTier multiTier;
	public NFMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, EnumMultiTier multiTier) {
		this(metaTileEntityId, new RecipeMap<?>[] {recipeMap}, multiTier);
	}
	
	public NFMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?>[] recipeMaps, EnumMultiTier multiTier) {
		super(metaTileEntityId, recipeMaps);
		this.recipeMapWorkable = new NFRecipeLogic(this);
		this.multiTier = multiTier;
	}
	
	@Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(TooltipHelper.BLINKING_CYAN + I18n.format("nf.tooltip.max_parallel") + " " + getParallelLimit());
        tooltip.add(TooltipHelper.RAINBOW + I18n.format("gregtech.machine.perfect_oc"));
    }
	
    public int getParallelLimit() {
        if(multiTier == EnumMultiTier.ADVANCED)
        {
        	return NFConfig.machineOptions.parallelLimitAdvanced;
        }
        if(multiTier == EnumMultiTier.SUPERCOMPUTER)
        {
        	return NFConfig.machineOptions.parallelLimitSupercomputer;
        }
        if(multiTier == EnumMultiTier.QUANTUMCOMPUTER)
        {
        	return NFConfig.machineOptions.parallelLimitQuantumcomputer;
        }
        if(multiTier == EnumMultiTier.COSMIC)
        {
        	return NFConfig.machineOptions.parallelLimitCosmic;
        }
        return 1;
    }
	
	protected class NFRecipeLogic extends MultiblockRecipeLogic {
        public NFRecipeLogic(NFMultiblockController tileEntity) {
            super(tileEntity, false);
        }

        @NotNull
        @Override
        public ParallelLogicType getParallelLogicType() {
            return ParallelLogicType.MULTIPLY;
        }
        
        @Override
        public int getParallelLimit() {
            if(multiTier == EnumMultiTier.ADVANCED)
            {
            	return NFConfig.machineOptions.parallelLimitAdvanced;
            }
            if(multiTier == EnumMultiTier.SUPERCOMPUTER)
            {
            	return NFConfig.machineOptions.parallelLimitSupercomputer;
            }
            if(multiTier == EnumMultiTier.QUANTUMCOMPUTER)
            {
            	return NFConfig.machineOptions.parallelLimitQuantumcomputer;
            }
            if(multiTier == EnumMultiTier.COSMIC)
            {
            	return NFConfig.machineOptions.parallelLimitCosmic;
            }
            return 1;
        }
    }
}
