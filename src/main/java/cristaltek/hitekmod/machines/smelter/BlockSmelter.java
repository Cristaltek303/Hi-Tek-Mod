package cristaltek.hitekmod.machines.smelter;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.blocks.BlockBase;
import cristaltek.hitekmod.handlers.GuiHandler.Gui;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSmelter extends BlockBase implements ITileEntityProvider
{

	public BlockSmelter()
	{
		super(Material.iron, "smelter");
		GameRegistry.registerTileEntity(TileEntitySmelter.class, "tileSmelter");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset)
	{
		player.openGui(HiTekMod.instance, Gui.SMELTER.ordinal(), world, x, y, z);
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntitySmelter();
	}
}
