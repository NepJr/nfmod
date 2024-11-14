package nepjr.nf.api.metatileentity;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.metatileentity.multiblock.MultiMapMultiblockController;
import gregtech.api.metatileentity.multiblock.ParallelLogicType;
import gregtech.api.recipes.RecipeMap;
import nepjr.nf.cfg.NFConfig;
import net.minecraft.util.ResourceLocation;

public abstract class NepRecipeMapMultiblockController extends MultiMapMultiblockController {

	public NepRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?> recipeMap) {
		this(metaTileEntityId, new RecipeMap<?>[] {recipeMap});
	}
	
	public NepRecipeMapMultiblockController(ResourceLocation metaTileEntityId, RecipeMap<?>[] recipeMaps) {
		super(metaTileEntityId, recipeMaps);
		this.recipeMapWorkable = new NepRecipeLogicThing(this);
	}
	
	protected class NepRecipeLogicThing extends MultiblockRecipeLogic {

        public NepRecipeLogicThing(NepRecipeMapMultiblockController tileEntity) {
            super(tileEntity, true); // all these machines will have perfect overclock because I'm a nice guy
        }

        @NotNull
        @Override
        public ParallelLogicType getParallelLogicType() {
            return ParallelLogicType.APPEND_ITEMS;
        }
        
        @Override
        public int getParallelLimit() {
            return NFConfig.machineOptions.parallelLimit;
        }
    }
}
