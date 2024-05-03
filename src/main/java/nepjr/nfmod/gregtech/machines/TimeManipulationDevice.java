package nepjr.nfmod.gregtech.machines;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.Widget;
import gregtech.api.gui.Widget.ClickData;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import nepjr.nfmod.gregtech.blocks.NFMachineCasings;
import nepjr.nfmod.gregtech.blocks.NFMetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

public class TimeManipulationDevice extends MultiblockWithDisplayBase
{
	private IEnergyContainer energyContainer;
	
	public TimeManipulationDevice(ResourceLocation metaTileEntityId) {
		super(metaTileEntityId);
	}
	
	protected void initializeAbilities() {
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.energyContainer = new EnergyContainerList(new ArrayList<>());
    }
    
    @Override
    protected void formStructure(PatternMatchContext context) 
    {
        super.formStructure(context);
        initializeAbilities();
    }

	@NotNull
	@Override
	protected  BlockPattern createStructurePattern() {
		return FactoryBlockPattern.start()
                .aisle("XXXXX", "XXXXX", "XXXXX", "XXXXX", "XXXXX")
                .aisle("XXXXX", "X###X", "X###X", "X###X", "X###X")
                .aisle("XXXXX", "X###X", "X###X", "X###X", "X###X")
                .aisle("XXXXX", "X###X", "X###X", "X###X", "X###X")
                .aisle("XXXXX", "XXXXX", "XXSXX", "XXXXX", "XXXXX")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setMaxGlobalLimited(1, 1)
                									.or(abilities(MultiblockAbility.INPUT_ENERGY).setMaxGlobalLimited(4, 2))))
                .where('#', states(NFMetaBlocks.nfMachineCasings.getState(NFMachineCasings.MetalCasingType.GRINDING_CASING)))
                .build();
	}

	public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
    }

	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		// TODO Auto-generated method stub
		return Textures.STABLE_TITANIUM_CASING;
	}
	
	public IEnergyContainer getEnergyContainer()
	{
		return this.energyContainer;
	}
	
	@Override
    public boolean isActive() {
        return isStructureFormed();
    }

	@Override
	public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
		// TODO Auto-generated method stub
		return new TimeManipulationDevice(metaTileEntityId);
	}
	
    @Override
    protected @NotNull Widget getFlexButton(int x, int y, int width, int height) {
        WidgetGroup group = new WidgetGroup(x, y, width, height);
        group.addWidget(new ClickButtonWidget(0, 0, 18, 18, "", this::incrementThrottle)
                .setButtonTexture(GuiTextures.BUTTON_THROTTLE_PLUS)
                .setTooltipText("gregtech.multiblock.large_boiler.throttle_increment"));
        return group;
    }

    private void incrementThrottle(ClickData clickData) {
        EntityPlayer p = getWorld().getClosestPlayer(getPos().getX(), getPos().getY(), getPos().getZ(), 8, false);
        
        if(!isStructureFormed())
        {
        	{ 
        		p.attackEntityFrom(new DamageSource("lostToTime").setDamageBypassesArmor().setDamageAllowedInCreativeMode(), Float.MAX_VALUE);
        		p.sendMessage(new TextComponentString("bruh what makes you think a non build time machine will work"));
        	}
        }
        
        else if (!getWorld().isRemote && !p.isRiding() && !p.isBeingRidden() && p.isNonBoss())
        {
        	if(getNumMaintenanceProblems() > 0)
        	{
        		p.sendMessage(new TextComponentString("half functioning time machines don't send you to the end!"));
        		p.attackEntityFrom(new DamageSource("lostToTime").setDamageBypassesArmor().setDamageAllowedInCreativeMode(), Float.MAX_VALUE);
        	}
        	else if (getEnergyContainer().getEnergyStored() < 500000000)
        	{
        		p.sendMessage(new TextComponentString("time machines without full power don't send you to the end"));
        		p.attackEntityFrom(new DamageSource("lostToTime").setDamageBypassesArmor().setDamageAllowedInCreativeMode(), Float.MAX_VALUE);
        		getEnergyContainer().removeEnergy(getEnergyContainer().getEnergyStored());
        	}
        	else 
        	{ 
        		p.changeDimension(1);
        		getEnergyContainer().removeEnergy(500000000);
        	}
        }
    }

	@Override
	protected void updateFormedValid() {
		// TODO Auto-generated method stub
		
	}

}
