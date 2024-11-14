package nepjr.nf.space.dims.station.overworld;

import javax.annotation.Nullable;

import nepjr.nf.api.space.dims.NFWorldProvider;
import nepjr.nf.cfg.NFConfig;
import nepjr.nf.space.dims.NFDimensions;
import nepjr.nf.space.dims.station.ChunkGeneratorSpaceStation;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderOverworldSpaceStation extends NFWorldProvider {

	@Override
	public DimensionType getDimensionType() {
		return NFDimensions.overworldSpaceStation;
	}
	
	@Override
	protected void init()
	{
		this.hasSkyLight = true;
		this.biomeProvider = new BiomeProviderSingle(Biomes.VOID);
		this.setAllowedSpawnTypes(false, false);
	}
	
	@Override
	public String getSaveFolder()
	{
		return "SPACESTATION_OVERWORLD";
	}

	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGeneratorSpaceStation(world);
	}
	
	@Override
	public boolean isDaytime()
    {
        return true;
    }
	
	@Override
	public float getSunBrightnessFactor(float par1)
    {
        return 1.0f;
    }
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 1.0F;
    }
	
	@Override
	public boolean canDoLightning(net.minecraft.world.chunk.Chunk chunk)
    {
        return false;
    }

	@Override
    public boolean canDoRainSnowIce(net.minecraft.world.chunk.Chunk chunk)
    {
        return false;
    }

	@Override
	@SideOnly(Side.CLIENT)
    public float getCloudHeight()
    {
        return 1.0F;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks)
    {
        return new Vec3d(0, 0, 0);
    }
	
	@Override
	@Nullable
    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks)
    {
        return null;
    }

	@Override
	public int getMoonPhase(long worldTime)
    {
        return 0;
    }
	
	@Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_)
    {
        return new Vec3d(0, 0, 0);
    }

	@Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored()
    {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1)
    {
        return 2.5f;
    }

	@Override
	public float getSolarEfficiency() {
		return NFConfig.solarEfficiencyOptions.overworldSpaceStationSolarEfficiency;
	}

}
