package nepjr.nfmod.gregtech;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;

public class NFMetaItems {
	
	private static StandardMetaItem metaItems;
	
	public static void initMetaItems()
	{
		metaItems = new StandardMetaItem();
		metaItems.setRegistryName("nf_meta_item");
	}
	
	public static MetaItem<?>.MetaValueItem TEST;
	
	public static void init()
	{
		registerSubItems();
	}
	
	private static void registerSubItems()
	{
		TEST = metaItems.addItem(0, "test_item");
	}
}
