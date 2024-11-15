package nepjr.nf.common.metatileentities.multiblock.generator;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.TextComponentUtil;
import gregtech.api.util.TextFormattingUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import nepjr.nf.api.capability.ISolarPanel;
import nepjr.nf.api.metatileentity.multiblock.NFMultiblockAbility;
import nepjr.nf.api.util.DimensionUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MetaTileEntitySolarArray extends MultiblockWithDisplayBase implements IControllable
{
	private final int tier;
	private boolean isWorkingEnabled;
	private List<ISolarPanel> solarPanels;
	int panels = 0;
	private long eut;
	private EnergyContainerList outputHatches;
	
	public MetaTileEntitySolarArray(ResourceLocation metaTileEntityId, int tier) {
		super(metaTileEntityId);
		this.tier = tier;
	}

	@Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) 
        {
        	if(!(getWorld().getWorldInfo().isRaining() || getWorld().getWorldInfo().isThundering()) && getWorld().isDaytime())
        	{
        		/*
        		 * There probably is a more efficient way to check each panel if they
        		 * can see the sun or not but this is the only method I could easily think
        		 * of that works. Probably unnecessarily increases the usage and might cause
        		 * TPS issues down the line in bases with a lot of these but oh well
        		 * 
        		 * future problem for future me
        		 * 
        		 * Granted it shouldn't be an issue so long as you remember to not block
        		 * the solar panel's sight to the sun
        		 */
        		panels = solarPanels.size();
        		for(int j = 0; j < solarPanels.size(); j++)
            	{
            		if(solarPanels.get(j).canSeeSun() == false)
            		{
            			panels--;
            		}
            	}
        		
        		if(panels == 0) // If somehow all the panels are blocked, invalidate the multiblock
        		{
        			invalidateStructure();
        		}
        		
        		// All should be good, add energy into the system
        		
            	eut = (long) Math.ceil((GTValues.V[solarPanels.get(0).tier()] * panels) * DimensionUtil.getSolarEfficiency(getWorld().provider));
            	if(eut > outputHatches.getOutputVoltage() * outputHatches.getOutputAmperage())
            	{
            		eut = (outputHatches.getOutputVoltage() * outputHatches.getOutputAmperage());
            	}
            	eut = eut * (6 - this.getNumMaintenanceProblems()) / 6;
            	outputHatches.addEnergy(eut);
        	}
        	else
        	{
        		eut = 0;
        	}
        }
    }
	
	@Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
        if(solarPanels.size() == 0) // You need atleast one panel to make it valid
        {
        	invalidateStructure();
        }
        /*
         *  Only run additional checks if there are actually panels, otherwise IndexOutOfBounds errors occur 
         *  also there's no point checking these if there aren't any in the first place lol
         */
        else 
        {
        	for(int i = 1; i < solarPanels.size(); i++) // All panels need to be the same tier
            {
            	if(solarPanels.get(i).tier() != solarPanels.get(i-1).tier())
            	{
            		invalidateStructure();
            	}
            }
            if(solarPanels.get(0).tier() > tier) // Panels can't be a higher tier than the controller
            {
            	invalidateStructure();
            }
        }
        for(int i = 0; i < solarPanels.size(); i++) // Make sure the solar panels can actually see the sun
        {
        	if(solarPanels.get(i).canSeeSun() == false)
    		{
    			invalidateStructure();
    		}
        }
    }

	private void initializeAbilities() {
		solarPanels = new ArrayList<>(getAbilities(NFMultiblockAbility.SOLAR_PANEL));
		List<IEnergyContainer> outputs = new ArrayList<>();
        outputs.addAll(getAbilities(MultiblockAbility.OUTPUT_ENERGY));
        outputs.addAll(getAbilities(MultiblockAbility.OUTPUT_LASER));
        this.outputHatches = new EnergyContainerList(outputs);
    }

	@Override
	protected @NotNull BlockPattern createStructurePattern() {
		if(tier <= GTValues.HV)
		{
			return FactoryBlockPattern.start()
					.aisle("OOOOO", "XXXXX")
					.aisle("OXXXO", "XPPPX")
					.aisle("OXXXO", "XPPPX")
					.aisle("OXXXO", "XPPPX")
					.aisle("OOYOO", "XXXXX")
					.where('Y', selfPredicate())
					.where('X', states(getCasingState()))
					.where('O', states(getCasingState())
							.or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setMaxGlobalLimited(1).setMinGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_ENERGY).setMaxGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_LASER).setMaxGlobalLimited(1)))
					.where('P', abilities(NFMultiblockAbility.SOLAR_PANEL)
							.or(abilities(MultiblockAbility.PASSTHROUGH_HATCH)))
					.build();
		}
		if(tier > GTValues.HV && tier <= GTValues.LuV)
		{
			return FactoryBlockPattern.start()
					.aisle("OOOOOOO", "XXXXXXX")
					.aisle("OXXXXXO", "XPPPPPX")
					.aisle("OXXXXXO", "XPPPPPX")
					.aisle("OXXXXXO", "XPPPPPX")
					.aisle("OXXXXXO", "XPPPPPX")
					.aisle("OXXXXXO", "XPPPPPX")
					.aisle("OOOYOOO", "XXXXXXX")
					.where('Y', selfPredicate())
					.where('X', states(getCasingState()))
					.where('O', states(getCasingState())
							.or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setMaxGlobalLimited(1).setMinGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_ENERGY).setMaxGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_LASER).setMaxGlobalLimited(1)))
					.where('P', abilities(NFMultiblockAbility.SOLAR_PANEL)
							.or(abilities(MultiblockAbility.PASSTHROUGH_HATCH)))
					.build();
		}
		if(tier > GTValues.LuV && tier <= GTValues.UHV)
		{
			return FactoryBlockPattern.start()
					.aisle("OOOOOOOOO", "XXXXXXXXX")
					.aisle("OXXXXXXXO", "XPPPPPPPX")
					.aisle("OXXXXXXXO", "XPPPPPPPX")
					.aisle("OXXXXXXXO", "XPPPPPPPX")
					.aisle("OXXXXXXXO", "XPPPPPPPX")
					.aisle("OXXXXXXXO", "XPPPPPPPX")
					.aisle("OXXXXXXXO", "XPPPPPPPX")
					.aisle("OXXXXXXXO", "XPPPPPPPX")
					.aisle("OOOOYOOOO", "XXXXXXXXX")
					.where('Y', selfPredicate())
					.where('X', states(getCasingState()))
					.where('O', states(getCasingState())
							.or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setMaxGlobalLimited(1).setMinGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_ENERGY).setMaxGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_LASER).setMaxGlobalLimited(1)))
					.where('P', abilities(NFMultiblockAbility.SOLAR_PANEL)
							.or(abilities(MultiblockAbility.PASSTHROUGH_HATCH)))
					.build();
		}
		if(tier > GTValues.UHV && tier <= GTValues.UIV)
		{
			return FactoryBlockPattern.start()
					.aisle("OOOOOOOOOOO", "XXXXXXXXXXX")
					.aisle("OXXXXXXXXXO", "XPPPPPPPPPX")
					.aisle("OXXXXXXXXXO", "XPPPPPPPPPX")
					.aisle("OXXXXXXXXXO", "XPPPPPPPPPX")
					.aisle("OXXXXXXXXXO", "XPPPPPPPPPX")
					.aisle("OXXXXXXXXXO", "XPPPPPPPPPX")
					.aisle("OXXXXXXXXXO", "XPPPPPPPPPX")
					.aisle("OXXXXXXXXXO", "XPPPPPPPPPX")
					.aisle("OXXXXXXXXXO", "XPPPPPPPPPX")
					.aisle("OXXXXXXXXXO", "XPPPPPPPPPX")
					.aisle("OOOOOYOOOOO", "XXXXXXXXXXX")
					.where('Y', selfPredicate())
					.where('X', states(getCasingState()))
					.where('O', states(getCasingState())
							.or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setMaxGlobalLimited(1).setMinGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_ENERGY).setMaxGlobalLimited(1).setPreviewCount(1))
							.or(abilities(MultiblockAbility.OUTPUT_LASER).setMaxGlobalLimited(1)))
					.where('P', abilities(NFMultiblockAbility.SOLAR_PANEL)
							.or(abilities(MultiblockAbility.PASSTHROUGH_HATCH)))
					.build();
		}
		if(tier > GTValues.UIV && tier <= GTValues.OpV)
		{
			return FactoryBlockPattern.start()
					.aisle("OOOOOOOOOOOOO", "XXXXXXXXXXXXX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXO", "XPPPPPPPPPPPX")
					.aisle("OOOOOOYOOOOOO", "XXXXXXXXXXXXX")
					.where('Y', selfPredicate())
					.where('X', states(getCasingState()))
					.where('O', states(getCasingState())
							.or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setMaxGlobalLimited(1).setMinGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_ENERGY).setMaxGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_LASER).setMaxGlobalLimited(1)))
					.where('P', abilities(NFMultiblockAbility.SOLAR_PANEL)
							.or(abilities(MultiblockAbility.PASSTHROUGH_HATCH)))
					.build();
		}
		if(tier == GTValues.MAX)
		{
			return FactoryBlockPattern.start()
					.aisle("OOOOOOOOOOOOOOO", "XXXXXXXXXXXXXXX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OXXXXXXXXXXXXXO", "XPPPPPPPPPPPPPX")
					.aisle("OOOOOOOYOOOOOOO", "XXXXXXXXXXXXXXX")
					.where('Y', selfPredicate())
					.where('X', states(getCasingState()))
					.where('O', states(getCasingState())
							.or(abilities(MultiblockAbility.MAINTENANCE_HATCH).setMaxGlobalLimited(1).setMinGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_ENERGY).setMaxGlobalLimited(1))
							.or(abilities(MultiblockAbility.OUTPUT_LASER).setMaxGlobalLimited(1)))
					.where('P', abilities(NFMultiblockAbility.SOLAR_PANEL)
							.or(abilities(MultiblockAbility.PASSTHROUGH_HATCH)))
					.build();
		}
		return null;
	}
	
	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		if(tier == GTValues.HV)
		{
			return Textures.CLEAN_STAINLESS_STEEL_CASING;
		}
		if(tier == GTValues.EV)
		{
			return Textures.STABLE_TITANIUM_CASING;
		}
		if(tier == GTValues.IV)
		{
			return Textures.ROBUST_TUNGSTENSTEEL_CASING;
		}
		return Textures.SOLID_STEEL_CASING;
	}
	
	private IBlockState getCasingState()
	{
		if(tier == GTValues.HV)
		{
			return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN);
		}
		if(tier == GTValues.EV)
		{
			return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
		}
		if(tier == GTValues.IV)
		{
			return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
		}
		return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STEEL_SOLID);
	}
	
	@Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }
	
	@Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(true, isActive())
                .addCustom(tl ->
                {         	
                	ITextComponent panelCount = TextComponentUtil.stringWithColor(
                            TextFormatting.GOLD,
                            TextFormattingUtil.formatNumbers(panels));
                	
                	ITextComponent efficiencyRating = TextComponentUtil.stringWithColor(
                            TextFormatting.GOLD,
                            TextFormattingUtil.formatNumbers(DimensionUtil.getSolarEfficiency(this.getWorld().provider) * 100) + "%");
                	
                	@SuppressWarnings("unused")
					ITextComponent providerName = TextComponentUtil.stringWithColor(
                			TextFormatting.GOLD, 
                			this.getWorld().provider.toString());
                	
                	ITextComponent energyPerTickPerPanel = TextComponentUtil.stringWithColor(
                            TextFormatting.GOLD,
                            TextFormattingUtil.formatNumbers(GTValues.V[solarPanels.get(0).tier()]));
                	
                	ITextComponent energyPerTick = TextComponentUtil.stringWithColor(
                            TextFormatting.GOLD,
                            TextFormattingUtil.formatNumbers(eut) + "EU/t");
                	
                	tl.add(TextComponentUtil.translationWithColor(TextFormatting.GREEN, "nf.gui.solar_array.solar_count", panelCount));
                	tl.add(TextComponentUtil.translationWithColor(TextFormatting.GREEN, "nf.gui.solar_array.efficiency", efficiencyRating));
                	tl.add(TextComponentUtil.translationWithColor(TextFormatting.GREEN, "nf.gui.solar_array.base_generation", energyPerTickPerPanel));
                	tl.add(TextComponentUtil.translationWithColor(TextFormatting.GREEN, "nf.gui.solar_array.actual_generation", energyPerTick));
                	// tl.add(TextComponentUtil.translationWithColor(TextFormatting.GREEN, "nf.gui.solar_array.debug.provider", providerName));
                });
    }
	
	@Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), isActive(),
                isWorkingEnabled());
    }
	
	@SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.RESEARCH_STATION_OVERLAY;
    }

	@Override
	public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
		// TODO Auto-generated method stub
		return new MetaTileEntitySolarArray(metaTileEntityId, tier);
	}

	@Override
	public boolean isWorkingEnabled() {
		return isWorkingEnabled;
	}

	@Override
	public void setWorkingEnabled(boolean isWorkingAllowed) {
		this.isWorkingEnabled = isWorkingAllowed;
		
	}

}
