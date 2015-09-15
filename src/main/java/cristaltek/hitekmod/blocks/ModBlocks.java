package cristaltek.hitekmod.blocks;

import cristaltek.hitekmod.machines.energycube.BlockEnergyCube;
import cristaltek.hitekmod.machines.smelter.BlockSmelter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks
{
	//Blocks
	public static Block darkstarBlock;
	public static Block darkIronBlock;
	//Machines
	public static Block energyCube;
	public static Block smelter;

	public static void init()
	{
		//Blocks
		darkstarBlock = new BlockBase(Material.rock, "darkstarBlock");
		darkIronBlock = new BlockBase(Material.iron, "darkIronBlock");
		//Machines
		energyCube = new BlockEnergyCube();
		smelter = new BlockSmelter();
	}
}
