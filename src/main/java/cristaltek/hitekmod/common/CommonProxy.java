package cristaltek.hitekmod.common;

import cpw.mods.fml.common.network.IGuiHandler;
import cristaltek.hitekmod.client.inventory.ContainerCraftingTablet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

	public void registerRenderInformation() {
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerCraftingTablet(player.inventory, world);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
}
