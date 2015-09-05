package cristaltek.hitekmod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {

	protected BlockBase(Material material, String name, Class<? extends ItemBlock> itemBlock) {
		super(material);
		setBlockName(name);
		setBlockTextureName("ht:" + name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		if (itemBlock != null)
			GameRegistry.registerBlock(this, itemBlock, name);
		else
			GameRegistry.registerBlock(this, name);
	}
	
	protected BlockBase(Material material, String name) {
		this(material, name, null);
	}
}
