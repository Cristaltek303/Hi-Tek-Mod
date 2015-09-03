package cristaltek.hitekmod.items;

import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.handlers.GuiHandler.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCraftingTablet extends ItemBase {

	public ItemCraftingTablet(String name) {
		super(name);
		
		//characteristics
		setMaxStackSize(1);
//		canRepair = false;
	}
	
	@Override
	public boolean getShareTag() {
		return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		if (!world.isRemote)
			player.openGui(HiTekMod.instance, Gui.CRAFTING_TABLET.ordinal(), world, (int)player.posX, (int)player.posY, (int)player.posZ);
		
		return item;
	}
}
