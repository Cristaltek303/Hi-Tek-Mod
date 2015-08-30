package cristaltek.hitekmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return "ht:textures/models/armor/XLF-1 Armor_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack) {
		ItemStack helmet = player.getCurrentArmor(3);
		ItemStack chestplate = player.getCurrentArmor(2);
		ItemStack leggings = player.getCurrentArmor(1);
		ItemStack boots = player.getCurrentArmor(0);
		
		if (helmet != null && helmet.getItem() instanceof ItemhtArmor &&
			chestplate != null && chestplate.getItem() instanceof ItemhtArmor &&
			leggings != null && leggings.getItem() instanceof ItemhtArmor &&
			boots != null && boots.getItem() instanceof ItemhtArmor) {
			// Full HiTek Armor
			player.capabilities.allowFlying = true;
			player.capabilities.setFlySpeed(0.2F);
		}
		else {
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
			player.capabilities.setFlySpeed(0.05F);
		}
	}
}
