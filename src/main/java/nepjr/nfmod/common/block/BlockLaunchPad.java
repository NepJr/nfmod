package nepjr.nfmod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLaunchPad extends Block {
    public BlockLaunchPad()
    {
        super(Material.IRON);
        this.setTranslationKey("launch_pad");
        this.setRegistryName("launch_pad");
        this.setSoundType(SoundType.METAL);
    }
}
