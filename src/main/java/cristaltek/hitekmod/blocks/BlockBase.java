package cristaltek.hitekmod.blocks;

import cristaltek.hitekmod.HiTekMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockBase extends Block {
	protected BlockBase(Material material, String name) {
		super(material);
		setUnlocalizedName(name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		
		setRegistryName(name);
		GameRegistry.register(this);
		
		ItemBlock itemBlock = new ItemBlock(this);
		itemBlock.setRegistryName(name);
		GameRegistry.register(itemBlock);
	}
}
