package cristaltek.hitekmod.blocks.rendering;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class BlockRenderer implements ISimpleBlockRenderingHandler
{
	public static int renderId = RenderingRegistry.getNextAvailableRenderId();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		int[] rotation = {0, 1, 3, 2};
		renderer.uvRotateTop = rotation[world.getBlockMetadata(x, y, z)];

		boolean flag = renderer.renderStandardBlock(block, x, y, z);
		renderer.uvRotateTop = 0;
		return flag;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return renderId;
	}
}
