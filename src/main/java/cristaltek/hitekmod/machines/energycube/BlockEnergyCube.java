package cristaltek.hitekmod.machines.energycube;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.blocks.BlockBase;
import cristaltek.hitekmod.handlers.GuiHandler.Gui;
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

public class BlockEnergyCube extends BlockBase implements ITileEntityProvider
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	public BlockEnergyCube()
	{
		super(Material.iron, "energyCube", ItemBlockEnergyCube.class);
		GameRegistry.registerTileEntity(TileEntityEnergyCube.class, "tileEnergyCube");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);
		this.iconTop = iconRegister.registerIcon("ht:EnergyCubeTop");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == 0 || side == 1)
			return this.iconTop;
		else
			return super.getIcon(side, meta);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOffset, float yOffset, float zOffset)
	{
		player.openGui(HiTekMod.instance, Gui.ENERGY_CUBE.ordinal(), world, x, y, z);
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
	{
		if (itemstack != null && itemstack.getItem() instanceof ItemBlockEnergyCube)
		{
			ItemBlockEnergyCube itemBlock = (ItemBlockEnergyCube)itemstack.getItem();
			TileEntityEnergyCube tileEntity = (TileEntityEnergyCube)world.getTileEntity(x, y, z);
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
		
		TileEntityEnergyCube tileEntity = (TileEntityEnergyCube)world.getTileEntity(x, y, z);
		
		Item item = getItemDropped(metadata, world.rand, fortune);
		if (item != null && item instanceof ItemBlockEnergyCube)
		{
			ItemBlockEnergyCube itemBlock = (ItemBlockEnergyCube)item;
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
		return new TileEntityEnergyCube();
	}
}
