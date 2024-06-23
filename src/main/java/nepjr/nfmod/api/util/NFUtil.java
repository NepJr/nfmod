package nepjr.nfmod.api.util;

import nepjr.nfmod.NFTags;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class NFUtil
{
    public static @NotNull ResourceLocation nfId(@NotNull String path)
    {
        return new ResourceLocation(NFTags.MODID, path);
    }
}
