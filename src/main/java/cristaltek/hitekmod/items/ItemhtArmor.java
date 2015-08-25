package cristaltek.hitekmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
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
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
		{
			if(this.armorType == 2)
			{
				return "ht:textures/models/armor/XLF-1 Armor_layer_2.png";
			}
			return "ht:textures/models/armor/XLF-1 Armor_layer_1.png";
		}

}