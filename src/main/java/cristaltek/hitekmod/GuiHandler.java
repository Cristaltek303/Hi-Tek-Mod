package cristaltek.hitekmod;

import cpw.mods.fml.common.network.IGuiHandler;
import cristaltek.hitekmod.client.gui.GuiCraftingTablet;
import cristaltek.hitekmod.client.inventory.ContainerCraftingTablet;
import cristaltek.hitekmod.client.inventory.InventoryCraftingTablet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerCraftingTablet(player, new InventoryCraftingTablet(player, CraftingTabletHelper.getIBench(player)));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiCraftingTablet(new ContainerCraftingTablet(player, new InventoryCraftingTablet(player, CraftingTabletHelper.getIBench(player))));
	}
}
