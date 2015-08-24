package cristaltek.hitekmod.items.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;


public class ItemhtArmor extends ItemArmor {
		
		//Armor Material
		public static final ArmorMaterial material = EnumHelper.addArmorMaterial("htArmorMaterial", -1, new int[]{3,8,6,3}, 35);

		public ItemhtArmor(String name, int type) {
			super(material, 0, type);
			setUnlocalizedName(name);
			setTextureName("ht:" + name);
			setCreativeTab(HiTekMod.tabHiTekMod);
			GameRegistry.registerItem(this, name);
			
			//Armor characteristics
			setMaxStackSize(1);
			setMaxDamage(0);
			canRepair = false;
		}

}