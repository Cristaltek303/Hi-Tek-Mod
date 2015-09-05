package cristaltek.hitekmod.machines.energycube;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.blocks.BlockBase;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockEnergyCube extends BlockBase implements ITileEntityProvider
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	public BlockEnergyCube()
	{
		super(Material.iron, "energyCube");
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
		player.openGui(HiTekMod.instance, 1, world, x, y, z);
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityEnergyCube();
	}

}
