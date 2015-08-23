package cristaltek.hitekmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class itemhtHelmet extends ItemArmor {
	
	//Armor Material
	public static final ArmorMaterial material = EnumHelper.addArmorMaterial("htArmorMaterial", -1, new int[]{3,8,6,3}, 35);

	public itemhtHelmet(String name, int type) {
		super(material, 0, type);
		setUnlocalizedName(name);
		setTextureName("ht:" + name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		GameRegistry.registerItem(this, name);
	}
}
