package nepjr.nf.api.space.dims;

import javax.annotation.Nullable;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class NFWorldProviderSpaceStation extends NFWorldProvider
{
	@Override
	protected void init()
	{
		this.hasSkyLight = true;
		this.biomeProvider = new BiomeProviderSingle(Biomes.VOID);
		this.setAllowedSpawnTypes(false, false);
	}
	
	@Override
	public void updateWeather()
    {
        this.world.getWorldInfo().setRaining(false);
        this.world.getWorldInfo().setThundering(false);
    }
	
	@Override
	public boolean isDaytime()
    {
        return true;
    }
	
	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGeneratorSpaceStation(world);
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
}
