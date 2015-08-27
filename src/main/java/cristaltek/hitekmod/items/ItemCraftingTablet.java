package cristaltek.hitekmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		player.openGui(HiTekMod.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);

		return super.onItemRightClick(item, world, player);
	}
}
