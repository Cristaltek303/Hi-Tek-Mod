package cristaltek.hitekmod.machines.energycube;

import java.util.ArrayList;
import java.util.List;

import cristaltek.hitekmod.reference.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

//TODO Add Slot for armor

public class GuiEnergyCube extends GuiContainer
{
	private TileEntityEnergyCube energyCube;

	public GuiEnergyCube(EntityPlayer player, TileEntityEnergyCube energyCube)
	{
		super(new ContainerEnergyCube(player, energyCube));
		xSize = 220;
		ySize = 193;
		
		this.energyCube = energyCube;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float ticks, int x, int y)
	{
		mc.getTextureManager().bindTexture(Textures.Gui.ENERGY_CUBE);
		
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		double percent = (double)this.energyCube.getEnergyStored(null) / (double)this.energyCube.getMaxEnergyStored(null);
		drawTexturedModalRect(xStart + 67, yStart + 41, 0, 205, (int)(percent * 84), 16);
		if (x >= this.guiLeft + 67 && x <= this.guiLeft + (67 + 84) &&
				y >= this.guiTop + 41 && y <= this.guiTop + (41 + 16))
		{
			List<String> tooltip = new ArrayList<String>();
			String stored = String.format("%,d", this.energyCube.getEnergyStored(null));
			String max = String.format("%,d", this.energyCube.getMaxEnergyStored(null));
			tooltip.add(stored + " / " + max + " RF");
			this.drawHoveringText(tooltip, x, y, fontRendererObj);
		}
	}
}
