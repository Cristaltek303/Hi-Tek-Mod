package cristaltek.hitekmod;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.blocks.ModBlocks;
import cristaltek.hitekmod.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {

	public static void init() {
		//Item Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.RawSilicon, 16), new Object[]{"A  ","B  ","C  ", 'A', Items.water_bucket, 'B', Blocks.sand, 'C', Blocks.dirt});//Raw Silicon
		GameRegistry.addSmelting(ModItems.RawSilicon, new ItemStack (ModItems.Silicon), 0.0F);//Silicon
		GameRegistry.addRecipe(new ItemStack(ModItems.CircuitBoard), new Object[]{"ABA","BAB","ABA", 'A', Items.gold_nugget, 'B', ModItems.Silicon});//Circuit Board
		GameRegistry.addRecipe(new ItemStack(ModItems.VIChip), new Object[]{"BAB","ACA","BAB", 'A', Items.gold_nugget, 'B', Items.redstone, 'C', ModItems.CircuitBoard});//V.I.-Chip
		GameRegistry.addRecipe(new ItemStack(ModItems.VIStabilizer),new Object[]{"ABA","BCB","ABA", 'A', ModItems.darkEnergyIngot, 'B', ModItems.CircuitBoard, 'C', ModItems.VIChip});//V.I.-Stabilizer
		GameRegistry.addRecipe(new ItemStack(ModItems.imbuer), new Object[]{" D ","DAD"," D ", 'A', ModItems.darkEnergyIngot, 'D', Items.diamond}); //Imbuer
		GameRegistry.addRecipe(new ItemStack(ModItems.concentratedDarkstar), new Object[]{"DDD","DID","DDD", 'D', ModItems.darkstar, 'I', ModItems.imbuer}); // Concentrated Darkstar
		GameRegistry.addRecipe(new ItemStack(ModItems.staffHandle), new Object[]{" D "," H "," D ", 'D', ModItems.darkstar, 'H', ModItems.handle}); //StaffHandle
		GameRegistry.addRecipe(new ItemStack(ModItems.handle), new Object[]{"  D"," D ","D  ", 'D', ModItems.darkEnergyIngot,}); //Handle
		GameRegistry.addRecipe(new ItemStack(ModItems.craftingChip), new Object[]{"ABA","BCB","ABA", 'A', Items.redstone, 'B', Blocks.crafting_table, 'C', ModItems.darkEnergyIngot});//Crafting Chip
		//Armor Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.htHelmet), new Object[]{"BAB","ACA","   ", 'A', ModItems.concentratedDarkstar, 'B', ModItems.darkEnergyIngot, 'C', Items.diamond_helmet});
		GameRegistry.addRecipe(new ItemStack(ModItems.htChestplate), new Object[]{"BCB","ABA","BAB", 'A', ModItems.concentratedDarkstar, 'B', ModItems.darkEnergyIngot, 'C', Items.diamond_chestplate});
		GameRegistry.addRecipe(new ItemStack(ModItems.htLeggings), new Object[]{"BAB","ACA","B B", 'A', ModItems.concentratedDarkstar, 'B', ModItems.darkEnergyIngot, 'C', Items.diamond_leggings});
		GameRegistry.addRecipe(new ItemStack(ModItems.htBoots), new Object[]{"   ","ACA","B B", 'A', ModItems.concentratedDarkstar, 'B', ModItems.darkEnergyIngot, 'C', Items.diamond_boots});
		//Tool Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.CraftingTablet),new Object[]{"CCC","DAD","BBB", 'A', ModItems.craftingChip, 'B', ModItems.darkEnergyIngot, 'C', Blocks.glass_pane, 'D', Blocks.stone_button});//CraftingTablet
		GameRegistry.addRecipe(new ItemStack(ModItems.TDTool),new Object[]{"AAA","ABA"," B ",'A', ModItems.concentratedDarkstar, 'B', ModItems.staffHandle});//TDTool
		GameRegistry.addRecipe(new ItemStack(ModItems.OPSword),new Object[]{" A "," A ","CBC", 'A', ModItems.concentratedDarkstar, 'B', ModItems.handle, 'C', ModItems.darkEnergyIngot});//OPSword 
		//Blocks
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.darkEnergyIngot,  9), new ItemStack(ModBlocks.darkIronBlock));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.darkIronBlock), new Object[]{"AAA", "AAA", "AAA", 'A', ModItems.darkEnergyIngot});//Dark Iron Block
		GameRegistry.addRecipe(new ItemStack(ModBlocks.darkstarBlock), new Object[]{"DDD","DDD","DDD", 'D', ModItems.darkstar}); //Darkstar Block
	}
}
