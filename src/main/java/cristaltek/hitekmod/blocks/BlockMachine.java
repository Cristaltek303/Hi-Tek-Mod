package cristaltek.hitekmod.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.blocks.rendering.BlockRenderer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockMachine extends BlockBase
{
	private IIcon iconTop, iconBottom;
	private IIcon iconSide, iconBack;

	protected BlockMachine(Material material, String name, Class<? extends ItemBlock> itemBlock)
	{
		super(material, name, itemBlock);
	}
	
	protected BlockMachine(Material material, String name)
	{
		this(material, name, null);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);
		iconTop = iconRegister.registerIcon("ht:MachineTop");
		iconBottom = iconRegister.registerIcon("ht:MachineBottom");
		iconSide = iconRegister.registerIcon("ht:MachineSide1");
		iconBack = iconRegister.registerIcon("ht:MachineBack");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		int[][] dir = {{3, 5, 4}, {4, 3, 2}, {2, 4, 5}, {5, 2, 3}}; // {back, left, right}
		
		if (side == 0)
			return iconBottom;
		else if (side == 1)
			return iconTop;
		else if (side == dir[meta][0])
			return iconBack;
		else if (side == dir[meta][1])
			return iconSide;
		else if (side == dir[meta][2])
			return new IconFlipped(iconSide, true, false);
		else
			return blockIcon;
	}
	
	@Override
	public int getRenderType()
	{
		return BlockRenderer.renderId;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
	{
		int side = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, side, 2);
	}
}
