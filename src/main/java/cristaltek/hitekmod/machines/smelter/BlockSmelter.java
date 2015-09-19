package cristaltek.hitekmod.machines.smelter;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.blocks.BlockBase;
import cristaltek.hitekmod.blocks.BlockMachine;
import cristaltek.hitekmod.handlers.GuiHandler.Gui;
import cristaltek.hitekmod.machines.energycube.ItemBlockEnergyCube;
import cristaltek.hitekmod.machines.energycube.TileEntityEnergyCube;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSmelter extends BlockMachine implements ITileEntityProvider
{
	public BlockSmelter()
	{
		super(Material.iron, "Smelter", ItemBlockSmelter.class);
		GameRegistry.registerTileEntity(TileEntitySmelter.class, "tileSmelter");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset)
	{
		player.openGui(HiTekMod.instance, Gui.SMELTER.ordinal(), world, x, y, z);
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
	{
		super.onBlockPlacedBy(world, x, y, z, player, itemstack);
		if (itemstack != null && itemstack.getItem() instanceof ItemBlockSmelter)
		{
			ItemBlockSmelter itemBlock = (ItemBlockSmelter)itemstack.getItem();
			TileEntitySmelter tileEntity = (TileEntitySmelter)world.getTileEntity(x, y, z);
			tileEntity.setEnergyStored(itemBlock.getEnergyStored(itemstack));
		}
	}
	
	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
	{
		if (willHarvest)
			return true;
		
		return super.removedByPlayer(world, player, x, y, z, willHarvest);
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.harvestBlock(world, player, x, y, z, meta);
		world.setBlockToAir(x, y, z);
	}
	
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		
		TileEntitySmelter tileEntity = (TileEntitySmelter)world.getTileEntity(x, y, z);
		
		Item item = getItemDropped(metadata, world.rand, fortune);
		if (item != null && item instanceof ItemBlockSmelter)
		{
			ItemBlockSmelter itemBlock = (ItemBlockSmelter)item;
			ItemStack itemstack = new ItemStack(itemBlock, 1, damageDropped(metadata));
			itemBlock.receiveEnergy(itemstack, tileEntity.getEnergyStored(null), false);
			ret.add(itemstack);
		}
		
		if (tileEntity != null)
		{
			for (int i = 0; i < tileEntity.getSizeInventory(); ++i)
			{
				ItemStack itemstack = tileEntity.getStackInSlot(i);
				if (itemstack != null)
					ret.add(itemstack);
			}
		}
		
		return ret;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntitySmelter();
	}
}
