package cristaltek.hitekmod.client.gui.button;

import org.lwjgl.opengl.GL11;

import cristaltek.hitekmod.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class TabButton extends GuiButton
{
	public static final int BALANCE = 0;
	public static final int CONFIG = 1;
	public static final int BACK = 2;
	
	private static int id = 0;
	private int side;
	private int icon;
	public boolean active;
	
	public TabButton(int xPos, int yPos, int width, int height, int side, int icon)
	{
		super(id++, xPos, yPos, width, height, "");
		
		this.side = side;
		this.icon = icon;
	}
	
	@Override
	public void drawButton(Minecraft minecraft, int mPosX, int mPosY)
	{
		if (this.visible)
		{
			minecraft.getTextureManager().bindTexture(Textures.Gui.BUTTONS);
			
			int tabOffsetX = 0;
			int tabOffsetY = (side == 0) ? 27 : 0;
			drawTexturedModalRect(xPosition, yPosition, tabOffsetX, tabOffsetY, 21, 21);
			
			boolean hover = mPosX >= this.xPosition && mPosY >= this.yPosition && mPosX < this.xPosition + this.width && mPosY < this.yPosition + this.height;
			
			int iconOffsetX = (active ? 32 : 0) + (hover ? 16 : 0);
			int iconOffsetY = 0;
			switch (icon)
			{
			case BALANCE:
				iconOffsetY = 60;
				break;
			case CONFIG:
				iconOffsetY = 76;
				break;
			case BACK:
				iconOffsetY = 92;
				break;
			}
			drawTexturedModalRect(xPosition + ((side == 0) ? 5 : 1), yPosition + 3, iconOffsetX, iconOffsetY, 15, 15);
		}
	}
}
