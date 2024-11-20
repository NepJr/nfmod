package nepjr.nf.api.metatileentity.multiblock;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.ParallelLogicType;
import gregtech.api.recipes.RecipeMap;
import nepjr.nf.api.data.EnumMultiTier;
import nepjr.nf.cfg.NFConfig;
import net.minecraft.util.ResourceLocation;

public abstract class NFMultiblockController extends MultiMapMultiblockController {

	private EnumMultiTier multiTier;
	public NFMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap, EnumMultiTier multiTier) {
		this(metaTileEntityId, new RecipeMap<?>[] {recipeMap}, multiTier);
	}
	
	public NFMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?>[] recipeMaps, EnumMultiTier multiTier) {
		super(metaTileEntityId, recipeMaps);
		this.recipeMapWorkable = new NepRecipeLogicThing(this);
		this.multiTier = multiTier;
	}
	
	protected class NepRecipeLogicThing extends MultiblockRecipeLogic {

        public NepRecipeLogicThing(NFMultiblockController tileEntity) {
            super(tileEntity, true); // all these machines will have perfect overclock because I'm a nice guy
        }

        @NotNull
        @Override
        public ParallelLogicType getParallelLogicType() {
            return ParallelLogicType.APPEND_ITEMS;
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
