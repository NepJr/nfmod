package nepjr.nf.common.metatileentities.multiblock.normal;

import org.jetbrains.annotations.NotNull;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import nepjr.nf.api.data.EnumMultiTier;
import nepjr.nf.api.metatileentity.multiblock.NFMultiblockController;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MetaTileEntityLargeCentrifugingMachine extends NFMultiblockController
{
	private EnumMultiTier multiTier;
	public MetaTileEntityLargeCentrifugingMachine(ResourceLocation metaTileEntityId, EnumMultiTier multiTier) {
		super(metaTileEntityId, RecipeMaps.CENTRIFUGE_RECIPES, multiTier);
		this.multiTier = multiTier;
	}

	@Override
	protected @NotNull BlockPattern createStructurePattern() {
		// TODO Auto-generated method stub
		return FactoryBlockPattern.start()
				.aisle("CCC", "CCC", "CCC")
				.aisle("CCC", "CCC", "CCC")
				.aisle("CCC", "CSC", "CCC")
				.where('S', selfPredicate())
				.where('C', states(getCasingState())
						.or(autoAbilities()))
				.build();
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		// TODO Auto-generated method stub
		return Textures.ROBUST_TUNGSTENSTEEL_CASING;
	}
	
    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TUNGSTENSTEEL_ROBUST);
    }
	
    @SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ROTOR_HOLDER_OVERLAY ;
    }
    
    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        if(this.isStructureFormed())
        {
        	Textures.LARGE_TURBINE_ROTOR_RENDERER.renderSided(renderState, translation, pipeline, getFrontFacing(),
                    this != null, true, this.isActive(), 16777215);
        }
    }

	@Override
	public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
		// TODO Auto-generated method stub
		return new MetaTileEntityLargeCentrifugingMachine(metaTileEntityId, multiTier);
	}

}
