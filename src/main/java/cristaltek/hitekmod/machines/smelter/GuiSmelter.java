package cristaltek.hitekmod.machines.smelter;

import java.util.ArrayList;
import java.util.List;

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
		double percent = (double)this.smelter.getEnergyStored(null) / (double)this.smelter.getMaxEnergyStored(null);
		drawTexturedModalRect(xStart + 162, yStart + 5, 0, 200, (int)(percent * 50), 5);
		if (x >= this.guiLeft + 162 && x <= this.guiLeft + (162 + 50) &&
				y >= this.guiTop + 5 && y <= this.guiTop + (5 + 5));
//		{
//			List<String> tooltip = new ArrayList<String>();
//			String stored = String.format("%,d", this.smelter.getEnergyStored(null));
//			String max = String.format("%,d", this.smelter.getMaxEnergyStored(null));
//			tooltip.add(stored + " / " + max + " RF");
//			this.drawHoveringText(tooltip, x, y, fontRendererObj);
//		}
	}
}
