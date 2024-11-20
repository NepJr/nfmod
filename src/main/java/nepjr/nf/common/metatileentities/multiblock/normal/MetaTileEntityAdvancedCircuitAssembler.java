package nepjr.nf.common.metatileentities.multiblock.normal;

import static gregtech.api.util.RelativeDirection.FRONT;
import static gregtech.api.util.RelativeDirection.RIGHT;
import static gregtech.api.util.RelativeDirection.UP;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.capability.IDataAccessHatch;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.recipeproperties.ResearchProperty;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiFluidHatch;
import nepjr.nf.api.data.EnumMultiTier;
import nepjr.nf.api.metatileentity.multiblock.NFMultiblockController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;

public class MetaTileEntityAdvancedCircuitAssembler extends NFMultiblockController 
{   
	private EnumMultiTier multiTier;
	public MetaTileEntityAdvancedCircuitAssembler(ResourceLocation metaTileEntityId, EnumMultiTier multiTier) 
	{
		super(metaTileEntityId, RecipeMaps.CIRCUIT_ASSEMBLER_RECIPES, multiTier);
		this.multiTier = multiTier;
	}

	@NotNull
    @Override
    protected BlockPattern createStructurePattern() {
        FactoryBlockPattern pattern = FactoryBlockPattern.start(FRONT, UP, RIGHT)
                .aisle("FIF", "RTR", "SAG", " Y ")
                .aisle("FIF", "RTR", "GAG", " Y ")
                .aisle("FIF", "RTR", "GAG", " Y ")
                .aisle("FIF", "RTR", "GAG", " Y ")
                .aisle("FIF", "RTR", "GAG", " Y ")
                .aisle("FIF", "RTR", "GAG", " Y ")
                .aisle("FOF", "RTR", "GAG", " Y ")
                .where('S', selfPredicate())
                .where('F', states(getCasingState())
                        .or(autoAbilities(false, true, false, false, false, false, false))
                        .or(fluidInputPredicate()))
                .where('O', abilities(MultiblockAbility.EXPORT_ITEMS)
                        .addTooltips("gregtech.multiblock.pattern.location_end"))
                .where('Y', states(getCasingState())
                        .or(abilities(MultiblockAbility.INPUT_ENERGY)
                                .setExactLimit(1)))
                .where('I', metaTileEntities(MetaTileEntities.ITEM_IMPORT_BUS[GTValues.ULV]))
                .where('G', states(getGrateState()))
                .where('A',
                        states(MetaBlocks.MULTIBLOCK_CASING
                                .getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_CONTROL)))
                .where('R', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.LAMINATED_GLASS)))
                .where('T',
                        states(MetaBlocks.MULTIBLOCK_CASING
                                .getState(BlockMultiblockCasing.MultiblockCasingType.ASSEMBLY_LINE_CASING)))
                .where(' ', any());
        return pattern.build();
    }
	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		// TODO Auto-generated method stub
		return Textures.SOLID_STEEL_CASING;
	}
	
	@NotNull
    protected static TraceabilityPredicate fluidInputPredicate() {
        // block multi-fluid hatches if ordered fluids is enabled
        if (ConfigHolder.machines.orderedFluidAssembly) {
            return metaTileEntities(MultiblockAbility.REGISTRY.get(MultiblockAbility.IMPORT_FLUIDS).stream()
                    .filter(mte -> !(mte instanceof MetaTileEntityMultiFluidHatch))
                    .toArray(MetaTileEntity[]::new))
                            .setMaxGlobalLimited(1);
        }
        return abilities(MultiblockAbility.IMPORT_FLUIDS);
    }
	
	@NotNull
    protected static IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
    }
	
	@Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(),
                recipeMapWorkable.isActive(), recipeMapWorkable.isWorkingEnabled());
    }
	
	@NotNull
    protected static IBlockState getGrateState() {
        return MetaBlocks.MULTIBLOCK_CASING.getState(BlockMultiblockCasing.MultiblockCasingType.GRATE_CASING);
    }
	
	@Override
    public boolean checkRecipe(@NotNull Recipe recipe, boolean consumeIfSuccess) {
        if (consumeIfSuccess) return true; // don't check twice
        // check ordered items
        if (ConfigHolder.machines.orderedAssembly) {
            List<GTRecipeInput> inputs = recipe.getInputs();
            List<IItemHandlerModifiable> itemInputInventory = getAbilities(MultiblockAbility.IMPORT_ITEMS);

            // slot count is not enough, so don't try to match it
            if (itemInputInventory.size() < inputs.size()) return false;

            for (int i = 0; i < inputs.size(); i++) {
                if (!inputs.get(i).acceptsStack(itemInputInventory.get(i).getStackInSlot(0))) {
                    return false;
                }
            }

            // check ordered fluids
            if (ConfigHolder.machines.orderedFluidAssembly) {
                inputs = recipe.getFluidInputs();
                List<IFluidTank> fluidInputInventory = getAbilities(MultiblockAbility.IMPORT_FLUIDS);

                // slot count is not enough, so don't try to match it
                if (fluidInputInventory.size() < inputs.size()) return false;

                for (int i = 0; i < inputs.size(); i++) {
                    if (!inputs.get(i).acceptsFluid(fluidInputInventory.get(i).getFluid())) {
                        return false;
                    }
                }
            }
        }

        if (!ConfigHolder.machines.enableResearch || !recipe.hasProperty(ResearchProperty.getInstance())) {
            return super.checkRecipe(recipe, consumeIfSuccess);
        }

        return isRecipeAvailable(getAbilities(MultiblockAbility.DATA_ACCESS_HATCH), recipe) ||
                isRecipeAvailable(getAbilities(MultiblockAbility.OPTICAL_DATA_RECEPTION), recipe);
    }

    private static boolean isRecipeAvailable(@NotNull Iterable<? extends IDataAccessHatch> hatches,
                                             @NotNull Recipe recipe) {
        for (IDataAccessHatch hatch : hatches) {
            // creative hatches do not need to check, they always have the recipe
            if (hatch.isCreative()) return true;

            // hatches need to have the recipe available
            if (hatch.isRecipeAvailable(recipe)) return true;
        }
        return false;
    }

	@Override
	public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
		// TODO Auto-generated method stub
		return new MetaTileEntityAdvancedCircuitAssembler(metaTileEntityId, multiTier);
	}

}
