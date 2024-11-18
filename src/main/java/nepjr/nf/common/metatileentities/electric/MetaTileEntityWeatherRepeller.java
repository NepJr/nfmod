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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.WorldInfo;

public class MetaTileEntityWeatherRepeller extends TieredMetaTileEntity
{

	public MetaTileEntityWeatherRepeller(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId, GTValues.IV);
	}
	
	@Override
    protected void reinitializeEnergyContainer() {
        long tierVoltage = GTValues.V[getTier()];
        this.energyContainer = EnergyContainerHandler.receiverContainer(this,
                tierVoltage * 16L, tierVoltage, getMaxInputOutputAmperage());
    }
	
	@Override
    protected long getMaxInputOutputAmperage() {
        return 1L;
    }
	
	@Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline)
    {
		super.renderMetaTileEntity(renderState, translation, pipeline);
		Textures.MAINTENANCE_OVERLAY.renderOriented(renderState, translation, pipeline, getFrontFacing());
    }
	
	@Override
	public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
		return new MetaTileEntityWeatherRepeller(metaTileEntityId);
	}

	@Override
	public void update()
	{
		super.update();
		if(this.energyContainer.getEnergyStored() > GTValues.VA[GTValues.IV])
		{
			WorldInfo worldInfo = getWorld().getWorldInfo() ;
			this.energyContainer.removeEnergy(GTValues.VA[GTValues.IV]);
			if(worldInfo.isRaining() || worldInfo.isThundering())
			{
				worldInfo.setCleanWeatherTime(12000);
                worldInfo.setRainTime(0);
                worldInfo.setThunderTime(0);
                worldInfo.setRaining(false);
                worldInfo.setThundering(false);
			}
		}
	}
	
	@Override
    public boolean getIsWeatherOrTerrainResistant() {
        return true;
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
