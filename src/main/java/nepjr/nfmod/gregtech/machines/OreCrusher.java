package nepjr.nfmod.gregtech.machines;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import nepjr.nfmod.gregtech.blocks.NFMachineCasings;
import nepjr.nfmod.gregtech.blocks.NFMetaBlocks;
import nepjr.nfmod.gregtech.recipes.NFRecipeMaps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OreCrusher extends RecipeMapMultiblockController
{

	public OreCrusher(ResourceLocation metaTileEntityId) 
	{
		super(metaTileEntityId, NFRecipeMaps.ORE_CRUSHER);
		this.recipeMapWorkable.setParallelLimit(16);
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
                .where('X', states(getCasingState()).or(autoAbilities(true, true, true, true, false, false, false)))
                .where('#', states(NFMetaBlocks.nfMachineCasings.getState(NFMachineCasings.MetalCasingType.GRINDING_CASING)))
                .build();
	}

	public IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.TITANIUM_STABLE);
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
		// TODO Auto-generated method stub
		return Textures.STABLE_TITANIUM_CASING;
	}
	
	@SideOnly(Side.CLIENT)
    @NotNull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.ROCK_BREAKER_OVERLAY;
    }

	@Override
	public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
		// TODO Auto-generated method stub
		return new OreCrusher(metaTileEntityId);
	}
	
	@Override
    public void addInformation(ItemStack stack, @Nullable World player, @NotNull List<String> tooltip,
                               boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("gregtech.universal.tooltip.parallel", 16));
    }

}
