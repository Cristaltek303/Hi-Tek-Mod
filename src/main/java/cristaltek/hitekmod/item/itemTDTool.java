package cristaltek.hitekmod.item;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class itemTDTool extends ItemPickaxe {

	public itemTDTool(ToolMaterial material) {
		super(material);
		
	}
@Override
public Set<String> getToolClasses (ItemStack stack) {
	return ImmutableSet.of("pickaxe", "spade");
    }
public static Set effectiveAgainst = Sets.newHashSet(new Block[] {
	    Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, 
	    Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, 
	    Blocks.soul_sand, Blocks.mycelium, Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, 
	    Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});

@Override
public float func_150893_a(ItemStack stack, Block block) {
    if (block.getMaterial() == Material.wood || block.getMaterial() == Material.vine || block.getMaterial() == Material.plants || block.getMaterial() == Material.leaves)
        return this.efficiencyOnProperMaterial;
    return effectiveAgainst.contains(block) ? this.efficiencyOnProperMaterial : super.func_150893_a(stack, block);
	}
//
//@Override
//	//Right Click breaks 1 Block
//	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
//		hitEntity(itemstack, )
//	return itemstack;
//	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ) {
		//System.out.println("target: " + x + ", " + y + ", " + z);
		int metadata = world.getBlockMetadata(x, y, z);
		Block block = world.getBlock(x, y, z);
		boolean willHarvest = block.canHarvestBlock(player, metadata);
		
		boolean isDestroyed = block.removedByPlayer(world, player, x, y, z, willHarvest);
		if (isDestroyed && willHarvest)
			block.harvestBlock(world, player, x, y, z, metadata);
		
		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
}

