package nepjr.nf.common.metatileentities.electric;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.PipelineUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntityWeatherRepeller extends TieredMetaTileEntity
{

	public MetaTileEntityWeatherRepeller(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, GTValues.IV);
		reinitializeEnergyContainer();
	}

	@Override
    protected void reinitializeEnergyContainer() {
		super.reinitializeEnergyContainer();
        this.energyContainer = EnergyContainerHandler.receiverContainer(this, GTValues.V[GTValues.IV] * 8, GTValues.IV, 1);
    }
	
	@Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline)
    {
		super.renderMetaTileEntity(renderState, translation, pipeline);
		Textures.MAINTENANCE_OVERLAY.renderOriented(renderState, translation, pipeline, getFrontFacing());
    }
	
	@Override
	public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
		// TODO Auto-generated method stub
		return new MetaTileEntityWeatherRepeller(metaTileEntityId);
	}

	@Override
	public void update()
	{
		if(this.energyContainer.getEnergyStored() > GTValues.V[GTValues.IV])
		{
			this.energyContainer.removeEnergy(GTValues.V[GTValues.IV]);
			getWorld().getWorldInfo().setRaining(false);
			getWorld().getWorldInfo().setThundering(false);
		}
	}
	
	@Override
    protected boolean openGUIOnRightClick() {
        return false;
    }
	
	@Override
	protected ModularUI createUI(EntityPlayer entityPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

}
