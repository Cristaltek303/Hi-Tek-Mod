package cristaltek.hitekmod.items;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemTDTool extends ItemPickaxe {
	
	//Tool/Weapons Materials
	public static final ToolMaterial material = EnumHelper.addToolMaterial("TDToolMaterial", 4, -1, 10000.0F, 1.0F, 35);

	public ItemTDTool(String name) {
		super(material);
		setUnlocalizedName(name);
		setTextureName("ht:" + name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		GameRegistry.registerItem(this, name);
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

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (player instanceof EntityPlayerMP)
			((EntityPlayerMP)player).theItemInWorldManager.onBlockClicked(x, y, z, side);
		
		return true;
	}
}

