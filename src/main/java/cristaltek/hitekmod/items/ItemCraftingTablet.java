package cristaltek.hitekmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.item.Item;

public class ItemCraftingTablet extends Item {

	public ItemCraftingTablet(String name) {
		setUnlocalizedName(name);
		setTextureName("ht:" + name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		GameRegistry.registerItem(this, name);
		
		//characteristics
		setMaxStackSize(1);
		canRepair = false;
	}
	
	
    
}
