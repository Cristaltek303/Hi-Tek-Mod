package cristaltek.hitekmod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {

	protected BlockBase(Material material, String name) {
		super(material);
		setBlockName(name);
		setBlockTextureName("ht:" + name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		GameRegistry.registerBlock(this, name);
	}
}
