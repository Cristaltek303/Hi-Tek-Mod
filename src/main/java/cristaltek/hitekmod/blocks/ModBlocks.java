package cristaltek.hitekmod.blocks;

import cristaltek.hitekmod.items.ItemBase;
import cristaltek.hitekmod.items.ItemTDTool;
import cristaltek.hitekmod.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ModBlocks {
	// Blocks
	public static Block DarkEnergyBlock;

	public static void init() {
		// Blocks
		DarkEnergyBlock = new BlockBase(Material.IRON, "DarkEnergyBlock");
	}
	
	public static void registerRenders() {
		registerRender(DarkEnergyBlock);
	}
	
	private static void registerRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}
