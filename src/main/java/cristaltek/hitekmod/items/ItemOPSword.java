package cristaltek.hitekmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemOPSword extends ItemSword
{
	//Sword Material
	public static final Item.ToolMaterial OPSwordmaterial = EnumHelper.addToolMaterial("OPSwordmaterial", 0, -1, 0.0F, 96, 50);
	
	public ItemOPSword(String name)
	{
		super(OPSwordmaterial);
		setUnlocalizedName(name);
		setTextureName("ht:" + name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		GameRegistry.registerItem(this, name);
		
		//Sword characteristics
		setMaxStackSize(1);
		canRepair = false;
	}
}
