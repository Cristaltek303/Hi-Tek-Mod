package cristaltek.hitekmod.handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import cristaltek.hitekmod.client.gui.GuiCraftingTablet;
import cristaltek.hitekmod.inventory.ContainerCraftingTablet;
import cristaltek.hitekmod.machines.energycube.ContainerEnergyCube;
import cristaltek.hitekmod.machines.energycube.GuiEnergyCube;
import cristaltek.hitekmod.machines.energycube.TileEntityEnergyCube;
import cristaltek.hitekmod.machines.smelter.ContainerSmelter;
import cristaltek.hitekmod.machines.smelter.GuiSmelter;
import cristaltek.hitekmod.machines.smelter.TileEntitySmelter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{

	public enum Gui
	{
		CRAFTING_TABLET, ENERGY_CUBE, SMELTER;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == Gui.CRAFTING_TABLET.ordinal())
			return new ContainerCraftingTablet(player);
		else if (ID == Gui.ENERGY_CUBE.ordinal())
		{
			TileEntityEnergyCube energyCube = (TileEntityEnergyCube)world.getTileEntity(x, y, z);
			return new ContainerEnergyCube(player, energyCube);
		}
		else if (ID == Gui.SMELTER.ordinal())
		{
			TileEntitySmelter smelter = (TileEntitySmelter)world.getTileEntity(x, y, z);
			return new ContainerSmelter(player, smelter);
		}
		else
			return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == Gui.CRAFTING_TABLET.ordinal())
			return new GuiCraftingTablet(player);
		else if (ID == Gui.ENERGY_CUBE.ordinal())
		{
			TileEntityEnergyCube energyCube = (TileEntityEnergyCube)world.getTileEntity(x, y, z);
			return new GuiEnergyCube(player, energyCube);
		}
		else if (ID == Gui.SMELTER.ordinal())
		{
			TileEntitySmelter smelter = (TileEntitySmelter)world.getTileEntity(x, y, z);
			return new GuiSmelter(player, smelter);
		}
		else
			return null;
	}
}
