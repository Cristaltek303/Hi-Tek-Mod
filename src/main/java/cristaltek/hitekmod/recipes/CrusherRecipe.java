package cristaltek.hitekmod.recipes;

import net.minecraft.item.ItemStack;

public class CrusherRecipe
{
	public ItemStack input;
	public ItemStack mainOutput, secOutput;
	public float chance;
	
	public CrusherRecipe(ItemStack input, ItemStack mainOutput, ItemStack secOutput, float chance)
	{
		this.input = input;
		this.mainOutput = mainOutput;
		this.secOutput = secOutput;
		this.chance = chance;
	}
}
