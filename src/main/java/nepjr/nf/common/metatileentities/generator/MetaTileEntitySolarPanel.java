package nepjr.nf.common.metatileentities.generator;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.EnergyContainerHandler;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.PipelineUtil;
import nepjr.nf.api.util.DimensionUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MetaTileEntitySolarPanel extends TieredMetaTileEntity
{

	long tierVoltage = GTValues.V[getTier()];
	protected boolean isWorkingEnabled;
	
	public MetaTileEntitySolarPanel(ResourceLocation metaTileEntityId, int tier) {
		super(metaTileEntityId, tier);
		reinitializeEnergyContainer();
	}
	
	@Override
    protected void reinitializeEnergyContainer() {
		super.reinitializeEnergyContainer();
        this.energyContainer = EnergyContainerHandler.emitterContainer(this, tierVoltage * 8, tierVoltage, 16);
        ((EnergyContainerHandler) this.energyContainer).setSideOutputCondition(side -> side == getFrontFacing());
    }

	@Override
    public void update() {
        super.update();
        if (!getWorld().isRemote) {
            World world = getWorld();
            if(isWorkingEnabled())
            {
            	this.energyContainer.addEnergy((long) (tierVoltage * DimensionUtil.getSolarEfficiency(world.provider)));
            }
        }
    }
	
	@Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntitySolarPanel(metaTileEntityId, getTier());
    }
	
	@Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline)
    {
		super.renderMetaTileEntity(renderState, translation, pipeline);
		Textures.SOLAR_PANEL.renderOriented(renderState, translation, pipeline, EnumFacing.UP);
		Textures.ENERGY_OUT.renderSided(getFrontFacing(), renderState, translation,
                PipelineUtil.color(pipeline, GTValues.VC[getTier()]));
    }
	
	@Override
    protected boolean isEnergyEmitter() {
        return true;
    }
	
	@Override
    protected long getMaxInputOutputAmperage() {
        return 16L;
    }
	
	@Override
    public boolean hasFrontFacing() {
        return true;
    }

	
	@Override
    protected boolean openGUIOnRightClick() {
        return false;
    }
	
	@Override
	protected ModularUI createUI(EntityPlayer entityPlayer) {
		return null;
	}

	public boolean isWorkingEnabled() {
		if(GTUtility.canSeeSunClearly(getWorld(), getPos()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
