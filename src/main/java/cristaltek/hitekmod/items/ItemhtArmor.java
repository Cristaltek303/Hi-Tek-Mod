package cristaltek.hitekmod.items;

import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.reference.Reference;
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
		if (this.armorType == 2) {
			return "ht:textures/models/armor/XLF-1 Armor_layer_2.png";
		}
		return "ht:textures/models/armor/XLF-1 Armor_layer_1.png";
	}
	
	//Fly-Test----------------------------------------------------------------------------------------------------
	
	

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack)
		{
				if (player.getCurrentArmor(3)!=null && 
					player.getCurrentArmor(2)!=null && 
					player.getCurrentArmor(1)!=null && 
					player.getCurrentArmor(0)!=null)
				{
					ItemStack helmet = player.getCurrentArmor(3);
					ItemStack chestplate = player.getCurrentArmor(2);
					ItemStack leggings = player.getCurrentArmor(1);
					ItemStack boots = player.getCurrentArmor(0);
					
					if(helmet.getItem()==ModItems.htHelmet &&
						chestplate.getItem()==ModItems.htChestplate &&
						leggings.getItem()==ModItems.htLeggings &&
						boots.getItem()==ModItems.htBoots)
					{
						player.capabilities.allowFlying = true;
						player.capabilities.setFlySpeed(2.0F);
						player.fallDistance = 0.0F;
					}
					else
					{
						

						
					}
				}
		}
		
}

