package cristaltek.hitekmod.items;

import cristaltek.hitekmod.HiTekMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemVCSword extends ItemSword {

	public static final ToolMaterial material = EnumHelper.addToolMaterial("VoidCarverMaterial", 0, 0, 0.0F, 96, 50);
	
	public ItemVCSword(String name) {
		super(material);
		setUnlocalizedName(name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		
		setRegistryName(name);
		GameRegistry.register(this);
		
		setMaxStackSize(1);
		canRepair = false;
	}


}
