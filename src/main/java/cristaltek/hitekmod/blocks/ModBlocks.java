package cristaltek.hitekmod.blocks;

import cristaltek.hitekmod.machines.crusher.BlockCrusher;
import cristaltek.hitekmod.machines.energycube.BlockEnergyCube;
import cristaltek.hitekmod.machines.smelter.BlockSmelter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks
{
	//Blocks
	public static Block DarkstarBlock;
	public static Block DarkIronBlock;
	//Machines
	public static Block EnergyCube;
	public static Block Smelter;
	public static Block Crusher;

	public static void init()
	{
		//Blocks
		DarkstarBlock = new BlockBase(Material.rock, "DarkstarBlock");
		DarkIronBlock = new BlockBase(Material.iron, "DarkIronBlock");
		//Machines
		EnergyCube = new BlockEnergyCube();
		Smelter = new BlockSmelter();
		Crusher = new BlockCrusher();
	}
}
