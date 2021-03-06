package cristaltek.hitekmod.client.gui.button;

import org.lwjgl.opengl.GL11;

import cristaltek.hitekmod.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class CraftingTabletButton extends GuiButton
{

	public static final int SPIN = 0;
	public static final int BALANCE = 1;
	public static final int EMPTY = 2;
	
	private int iconOffsetX = 0;
	private int iconOffsetY = 0;
	
	public CraftingTabletButton(int id, int xPos, int yPos, int width, int height, int type)
	{
		super(id, xPos, yPos, width, height, "");
		
		if (type == SPIN)
		{
			iconOffsetX = 0;
			iconOffsetY = 211;
		}
		else if (type == BALANCE)
		{
			iconOffsetX = 0;
			iconOffsetY = 227;
		}
		else if (type == EMPTY)
		{
			iconOffsetX = 0;
			iconOffsetY = 195;
		}
	}
	
	@Override
	public void drawButton(Minecraft minecraft, int mPosX, int mPosY)
	{
		if (this.visible)
		{
			minecraft.getTextureManager().bindTexture(Textures.Gui.CRAFTING_TABLET);
			
			boolean hover = mPosX >= this.xPosition && mPosY >= this.yPosition && mPosX < this.xPosition + this.width && mPosY < this.yPosition + this.height;
			int h = getHoverState(hover);
			
			int fromLeft = iconOffsetX + (h-1) * 16;
			
			GL11.glDisable(GL11.GL_BLEND);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, fromLeft, iconOffsetY, 13, 13);
		}
	}
}
