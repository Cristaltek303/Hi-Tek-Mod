package cristaltek.hitekmod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.machines.smelter.ItemBlockSmelter;
import cristaltek.hitekmod.machines.smelter.TileEntitySmelter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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
		switch (side)
		{
		case 0:
			return iconBottom;
		case 1:
			return iconTop;
		case 3:
			return iconBack;
		case 4:
			return new IconFlipped(iconSide, true, false);
		case 5:
			return iconSide;
		default:
			return super.getIcon(side, meta);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemstack)
	{
		int side = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, side, 2);
	}
}
