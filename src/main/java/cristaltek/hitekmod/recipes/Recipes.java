package cristaltek.hitekmod.recipes;

import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.blocks.ModBlocks;
import cristaltek.hitekmod.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes
{
	private static HashMap crusherRecipes = new HashMap();

	public static void init()
	{
		//Item Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.RawSilicon, 16), new Object[]{"A  ","B  ","C  ", 'A', Items.water_bucket, 'B', Blocks.sand, 'C', Blocks.dirt});//Raw Silicon
		GameRegistry.addSmelting(ModItems.RawSilicon, new ItemStack (ModItems.Silicon), 0.0F);//Silicon
		GameRegistry.addRecipe(new ItemStack(ModItems.CircuitBoard), new Object[]{"ABA","BAB","ABA", 'A', Items.gold_nugget, 'B', ModItems.Silicon});//Circuit Board
		GameRegistry.addRecipe(new ItemStack(ModItems.VIChip), new Object[]{"BAB","ACA","BAB", 'A', Items.gold_nugget, 'B', Items.redstone, 'C', ModItems.CircuitBoard});//V.I.-Chip
		GameRegistry.addRecipe(new ItemStack(ModItems.VIStabilizer),new Object[]{"ABA","BCB","ABA", 'A', ModItems.DarkEnergyIngot, 'B', ModItems.CircuitBoard, 'C', ModItems.VIChip});//V.I.-Stabilizer
		GameRegistry.addRecipe(new ItemStack(ModItems.Imbuer), new Object[]{" D ","DAD"," D ", 'A', ModItems.DarkEnergyIngot, 'D', Items.diamond}); //Imbuer
		GameRegistry.addRecipe(new ItemStack(ModItems.ConcentratedDarkstar), new Object[]{"DDD","DID","DDD", 'D', ModItems.Darkstar, 'I', ModItems.Imbuer}); // Concentrated Darkstar
		GameRegistry.addRecipe(new ItemStack(ModItems.StaffHandle), new Object[]{" D "," H "," D ", 'D', ModItems.Darkstar, 'H', ModItems.Handle}); //StaffHandle
		GameRegistry.addRecipe(new ItemStack(ModItems.Handle), new Object[]{"  D"," D ","D  ", 'D', ModItems.DarkEnergyIngot,}); //Handle
		GameRegistry.addRecipe(new ItemStack(ModItems.CraftingChip), new Object[]{"ABA","BCB","ABA", 'A', Items.redstone, 'B', Blocks.crafting_table, 'C', ModItems.DarkEnergyIngot});//Crafting Chip
		//Armor Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.htHelmet), new Object[]{"BAB","ACA","   ", 'A', ModItems.ConcentratedDarkstar, 'B', ModItems.DarkEnergyIngot, 'C', Items.diamond_helmet});
		GameRegistry.addRecipe(new ItemStack(ModItems.htChestplate), new Object[]{"BCB","ABA","BAB", 'A', ModItems.ConcentratedDarkstar, 'B', ModItems.DarkEnergyIngot, 'C', Items.diamond_chestplate});
		GameRegistry.addRecipe(new ItemStack(ModItems.htLeggings), new Object[]{"BAB","ACA","B B", 'A', ModItems.ConcentratedDarkstar, 'B', ModItems.DarkEnergyIngot, 'C', Items.diamond_leggings});
		GameRegistry.addRecipe(new ItemStack(ModItems.htBoots), new Object[]{"   ","ACA","B B", 'A', ModItems.ConcentratedDarkstar, 'B', ModItems.DarkEnergyIngot, 'C', Items.diamond_boots});
		//Tool Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.CraftingTablet),new Object[]{"CCC","DAD","BBB", 'A', ModItems.CraftingChip, 'B', ModItems.DarkEnergyIngot, 'C', Blocks.glass_pane, 'D', Blocks.stone_button});//CraftingTablet
		GameRegistry.addRecipe(new ItemStack(ModItems.TDTool),new Object[]{"AAA","ABA"," B ",'A', ModItems.ConcentratedDarkstar, 'B', ModItems.StaffHandle});//TDTool
		GameRegistry.addRecipe(new ItemStack(ModItems.OPSword),new Object[]{" A "," A ","CBC", 'A', ModItems.ConcentratedDarkstar, 'B', ModItems.Handle, 'C', ModItems.DarkEnergyIngot});//OPSword
		GameRegistry.addRecipe(new ItemStack(ModItems.Magnet), new Object[]{"C B","DAD","B C", 'A', ModItems.VIChip, 'B', Items.redstone, 'C', new ItemStack(Items.dye, 1, 4), 'D', ModItems.DarkEnergyIngot});
		//Blocks
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.DarkEnergyIngot,  9), new ItemStack(ModBlocks.DarkIronBlock));
		GameRegistry.addRecipe(new ItemStack(ModBlocks.DarkIronBlock), new Object[]{"AAA", "AAA", "AAA", 'A', ModItems.DarkEnergyIngot});//Dark Iron Block
		GameRegistry.addRecipe(new ItemStack(ModBlocks.DarkstarBlock), new Object[]{"DDD","DDD","DDD", 'D', ModItems.Darkstar}); //Darkstar Block
		
		// Crusher Recipes
		addCrusherRecipe(new ItemStack(Blocks.coal_ore), new ItemStack(Items.coal, 5), new ItemStack(Items.diamond), 0.5F);
	}
	
	public static void addCrusherRecipe(ItemStack input, ItemStack mainOutput, ItemStack secOutput, float chance)
	{
		crusherRecipes.put(input.getItem(), new CrusherRecipe(input, mainOutput, secOutput, chance));
	}
	
	public static CrusherRecipe getCrusherRecipe(ItemStack input)
	{
		if (crusherRecipes.containsKey(input.getItem()))
			return (CrusherRecipe)crusherRecipes.get(input.getItem());
		else
			return null;
	}
}
