package cristaltek.hitekmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.client.gui.GuiCraftingTablet;
import net.minecraft.client.Minecraft;
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
		
		Minecraft.getMinecraft().displayGuiScreen(new GuiCraftingTablet());
		
		return super.onItemRightClick(item, world, player);
	}
    
}
