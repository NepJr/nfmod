package nepjr.nf.common.metatileentities.multiblock.normal;

import org.jetbrains.annotations.NotNull;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.ICubeRenderer;
import nepjr.nf.api.metatileentity.multiblock.NFMultiblockController;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityLargeCentrifugingMachine extends NFMultiblockController
{

	public MetaTileEntityLargeCentrifugingMachine(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, RecipeMaps.CENTRIFUGE_RECIPES);
	}

	@Override
	protected @NotNull BlockPattern createStructurePattern() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
		// TODO Auto-generated method stub
		return new MetaTileEntityLargeCentrifugingMachine(metaTileEntityId);
	}

}
