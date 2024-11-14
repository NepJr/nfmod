package nepjr.nf.api.space.dims;

import net.minecraft.world.WorldProvider;

public abstract class NFWorldProvider extends WorldProvider {

	public abstract float getSolarEfficiency();

	@Override
	public abstract String getSaveFolder();
}
