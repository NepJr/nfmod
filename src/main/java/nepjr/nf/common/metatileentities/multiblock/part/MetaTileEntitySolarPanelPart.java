package nepjr.nf.common.metatileentities.multiblock.part;

import java.util.List;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.utils.PipelineUtil;
import gregtech.common.metatileentities.multi.multiblockpart.MetaTileEntityMultiblockPart;
import nepjr.nf.api.capability.ISolarPanel;
import nepjr.nf.api.metatileentity.multiblock.NFMultiblockAbility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class MetaTileEntitySolarPanelPart extends MetaTileEntityMultiblockPart 
										  implements IMultiblockAbilityPart<ISolarPanel>, ISolarPanel
{
	protected final int tier;
	public MetaTileEntitySolarPanelPart(ResourceLocation metaTileEntityId, int tier) 
	{
		super(metaTileEntityId, tier);
		this.tier = tier;
	}

	@Override
	public MultiblockAbility<ISolarPanel> getAbility() {
		return NFMultiblockAbility.SOLAR_PANEL;
	}

	@Override
	public void registerAbilities(List<ISolarPanel> abilityList) 
	{
		abilityList.add(this);
	}
	
	@Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline)
    {
		super.renderMetaTileEntity(renderState, translation, pipeline);
		Textures.SOLAR_PANEL.renderOriented(renderState, translation, pipeline, EnumFacing.UP);
    }

	@Override
	public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
		// TODO Auto-generated method stub
		return new MetaTileEntitySolarPanelPart(metaTileEntityId, tier);
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

	@Override
	public boolean canSeeSun() {
		return GTUtility.canSeeSunClearly(getWorld(), getPos());
	}

	@Override
	public int tier() {
		return tier;
	}

}
