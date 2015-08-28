package cristaltek.hitekmod.client.gui.button;

import cristaltek.hitekmod.reference.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;



public class CraftingTabletButton extends GuiButton  {
	
	public static final int SPIN = 0;
    public static final int BALANCE = 1;
    public static final int EMPTY = 2;
    
   public static final ResourceLocation widgetTextures = Textures.Gui.WIDGETS;
   
   private int iconOffsetX = 0;
   private int iconOffsetY = 0;
	

	public CraftingTabletButton(int id, int xPos, int yPos, int width, int height, int type) {
		super(id, xPos, yPos, width, height, "");
		
		if (type == SPIN) {
            iconOffsetX = 64;
            iconOffsetY = 16;
        } else if (type == BALANCE) {
            iconOffsetX = 64;
            iconOffsetY = 32;
        } else if (type == EMPTY) {
            iconOffsetX = 64;
            iconOffsetY = 0;
            }
        }
		
		@Override
	    public void drawButton(Minecraft minecraft, int mPosX, int mPosY) {
	        if (this.visible) {
	            minecraft.getTextureManager().bindTexture(widgetTextures);

	            boolean hover = mPosX >= this.xPosition && mPosY >= this.yPosition && mPosX < this.xPosition + this.width && mPosY < this.yPosition + this.height;
	            int h = getHoverState(hover);

	            int fromLeft = iconOffsetX + (h-1) * 16;

	            this.drawTexturedModalRect(this.xPosition, this.yPosition, fromLeft, iconOffsetY, 16, 16);
	        }
		
	}
	
	

}
