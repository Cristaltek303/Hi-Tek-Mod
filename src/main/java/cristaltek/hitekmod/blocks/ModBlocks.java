package cristaltek.hitekmod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	
	//Blocks
	public static Block darkstarBlock;
	public static Block DarkIronBlock;

	public static void init() {
		
		//Blocks-------------------------------------------------------------------------------------------------------------------------------------------
		
		darkstarBlock = new BlockBase(Material.rock, "darkstarBlock");
		DarkIronBlock = new BlockBase(Material.iron,"DarkIronBlock");
	}
}
