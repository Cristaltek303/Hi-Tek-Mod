package cristaltek.hitekmod.machines.smelter;

import cristaltek.hitekmod.machines.energycube.TileEntityEnergyCube;
import cristaltek.hitekmod.reference.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

public class GuiSmelter extends GuiContainer
{
	private TileEntitySmelter smelter;

	public GuiSmelter(EntityPlayer player, TileEntitySmelter smelter)
	{
		super(new ContainerSmelter(player, smelter));
		xSize = 240;
		ySize = 193;
		
		this.smelter = smelter;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float ticks, int x, int y)
	{
		mc.getTextureManager().bindTexture(Textures.Gui.SMELTER);
		
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		
	}

}
