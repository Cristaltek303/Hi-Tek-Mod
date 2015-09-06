package cristaltek.hitekmod.blocks;

import cristaltek.hitekmod.machines.energycube.BlockEnergyCube;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks
{
	//Blocks
	public static Block darkstarBlock;
	public static Block darkIronBlock;
	public static Block energyCube;

	public static void init()
	{
		//Blocks
		darkstarBlock = new BlockBase(Material.rock, "darkstarBlock");
		darkIronBlock = new BlockBase(Material.iron, "darkIronBlock");
		energyCube = new BlockEnergyCube();
	}
}
