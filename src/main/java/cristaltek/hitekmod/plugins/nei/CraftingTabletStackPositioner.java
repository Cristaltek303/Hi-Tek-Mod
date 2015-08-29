package cristaltek.hitekmod.plugins.nei;

import java.util.ArrayList;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IStackPositioner;
import codechicken.nei.recipe.GuiRecipe;
import cristaltek.hitekmod.client.gui.GuiCraftingTablet;
import net.minecraft.client.Minecraft;

public class CraftingTabletStackPositioner implements IStackPositioner {

	@Override
	public ArrayList<PositionedStack> positionStacks(ArrayList<PositionedStack> stacks) {

        if (Minecraft.getMinecraft().currentScreen instanceof GuiRecipe)
        {
            GuiRecipe recipeGui = (GuiRecipe) Minecraft.getMinecraft().currentScreen;

            if (!(recipeGui.firstGui instanceof GuiCraftingTablet)) {
                System.out.println("No iBenchGui found!");
                return stacks;
            }

            for (PositionedStack stack : stacks) {
                stack.relx += 17;
                stack.rely += 11;
            }
        }

        return stacks;
	}

}
