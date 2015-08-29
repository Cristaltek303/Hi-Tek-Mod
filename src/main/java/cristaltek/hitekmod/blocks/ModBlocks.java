package cristaltek.hitekmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	//Blocks
	public static Block darkstarBlock;
	public static Block darkIronBlock;

	public static void init() {
		//Blocks
		darkstarBlock = new BlockBase(Material.rock, "darkstarBlock");
		darkIronBlock = new BlockBase(Material.iron, "darkIronBlock");
	}
}
