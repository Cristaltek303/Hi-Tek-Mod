package cristaltek.hitekmod;

import cristaltek.hitekmod.items.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModCrafting {
	
	public static void register() {
		//Tools
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.TDTool), "aaa", "aba", " a ", 'a', ModItems.ConcentratedDarkstar, 'b', ModItems.StaffHandle);
		
		//Items
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.RawSilicon,16), new ItemStack(Items.WATER_BUCKET), new ItemStack(Items.COAL), new ItemStack(Blocks.DIRT), new ItemStack(Items.CLAY_BALL)); //RawSilicon
		GameRegistry.addSmelting(ModItems.RawSilicon, new ItemStack(ModItems.Silicon,4), 0.1F); //Silicon
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.CircuitBoard), " c ", "cdc", "bab", 'a', ModItems.Silicon, 'b', Items.IRON_INGOT, 'c', Items.REDSTONE, 'd', ModItems.DarkEnergyIngot); //CircuitBoard
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.VIChip), "cdc", "bab", "cdc", 'a', ModItems.CircuitBoard, 'b', ModItems.DarkEnergyIngot, 'c', Items.REDSTONE, 'd', ModItems.Silicon); //VIChip
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.CraftingChip), " c ", "bab", " c ", 'a', ModItems.VIChip, 'b', Blocks.CRAFTING_TABLE, 'c', ModItems.DarkEnergyIngot); //CraftingChip
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.VIStabilizer), "cbc", "bab", "cbc", 'a', ModItems.VIChip, 'b', ModItems.CircuitBoard, 'c', ModItems.DarkEnergyIngot); //VIStabilizer
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.Imbuer), "aba", "bcb", "aba", 'a', ModItems.DarkEnergyIngot, 'b', ModItems.InfusedGoldIngot, 'c', ModItems.VIChip); //Imbuer
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.ConcentratedDarkstar), "cbc", "bab", "cbc", 'a', ModItems.Imbuer, 'b', ModItems.Darkstar, 'c', ModItems.InfusedGoldIngot);//ConcentratedDarkstar
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.Handle), "  a", " a ", "a  ", 'a', ModItems.DarkEnergyIngot); //Handle
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.StaffHandle), " b ", " a ", " b ", 'a', ModItems.Handle, 'b', ModItems.Darkstar); //StaffHandle
	}

}
