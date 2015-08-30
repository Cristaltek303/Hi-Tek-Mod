package cristaltek.hitekmod.plugins.nei;

import java.util.ArrayList;

import codechicken.nei.PositionedStack;
import codechicken.nei.api.IStackPositioner;

public class PreciseStackPositioner implements IStackPositioner {

	private int xStart, yStart;
	private int xSpacing, ySpacing;
	
	public PreciseStackPositioner(int xStart, int yStart, int xSpacing, int ySpacing) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xSpacing = xSpacing;
		this.ySpacing = ySpacing;
	}
	
	@Override
	public ArrayList<PositionedStack> positionStacks(ArrayList<PositionedStack> stacks) {
		for (PositionedStack stack : stacks) {
			// Calculate row & column from hard-coded NEI stack position
			int col = (stack.relx - 25) / 18;
			int row = (stack.rely - 6) / 18;
			
			stack.relx = this.xStart + col * this.xSpacing;
			stack.rely = this.yStart + row * this.ySpacing;
		}
		return stacks;
	}
}
