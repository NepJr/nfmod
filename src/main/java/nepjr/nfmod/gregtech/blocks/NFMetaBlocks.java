package nepjr.nfmod.gregtech.blocks;

public class NFMetaBlocks 
{
	private NFMetaBlocks() {}
	
	public static NFMachineCasings nfMachineCasings;
	
	public static void init()
	{
		nfMachineCasings = new NFMachineCasings();
		nfMachineCasings.setRegistryName("nf_machine_casings");
	}
}
