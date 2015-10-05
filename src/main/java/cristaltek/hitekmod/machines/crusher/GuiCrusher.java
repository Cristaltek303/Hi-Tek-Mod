package cristaltek.hitekmod.machines.crusher;

import java.util.ArrayList;
import java.util.List;

import cristaltek.hitekmod.client.gui.GuiConfigurable;
import cristaltek.hitekmod.client.gui.button.CraftingTabletButton;
import cristaltek.hitekmod.client.gui.button.TabButton;
import cristaltek.hitekmod.inventory.ContainerCraftingTablet;
import cristaltek.hitekmod.network.PacketHandler;
import cristaltek.hitekmod.network.message.BalanceMessage;
import cristaltek.hitekmod.network.message.CraftingTabletMessage;
import cristaltek.hitekmod.reference.Textures;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

public class GuiCrusher extends GuiConfigurable
{
	private TileEntityCrusher crusher;
	
	private TabButton balanceTabButton;

	public GuiCrusher(EntityPlayer player, TileEntityCrusher crusher)
	{
		super(new ContainerCrusher(player, crusher));
		
		this.crusher = crusher;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		super.initGui();
		
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
		buttonList.add(balanceTabButton = new TabButton(xStart - 20, yStart + 17, 20, 21, 0, TabButton.BALANCE));
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y)
	{
		balanceTabButton.active = crusher.balance;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float ticks, int x, int y)
	{
		mc.getTextureManager().bindTexture(Textures.Gui.CRUSHER);
		
		// Draw GUI background
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		// Draw progress arrows
		for (int i = 0; i < 4; i++)
			drawTexturedModalRect(xStart + 31 + i * 44, yStart + 40, 0, 210, 24, (int)(crusher.getCrushProgress(i) * 18));
		// Draw energy bar
		double percent = (double)this.crusher.getEnergyStored(null) / (double)this.crusher.getMaxEnergyStored(null);
		drawTexturedModalRect(xStart + 162, yStart + 5, 0, 200, (int)(percent * 50), 5);
		if (x >= this.guiLeft + 162 && x <= this.guiLeft + (162 + 50) &&
				y >= this.guiTop + 5 && y <= this.guiTop + (5 + 5))
		{
			List<String> tooltip = new ArrayList<String>();
			String stored = String.format("%,d", this.crusher.getEnergyStored(null));
			String max = String.format("%,d", this.crusher.getMaxEnergyStored(null));
			tooltip.add(stored + " / " + max + " RF");
			this.drawHoveringText(tooltip, x, y, fontRendererObj);
		}
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button == balanceTabButton)
		{
			crusher.balance = !crusher.balance;
			TabButton button1 = (TabButton)button;
			button1.active = crusher.balance;
			PacketHandler.INSTANCE.sendToServer(new BalanceMessage(crusher.xCoord, crusher.yCoord, crusher.zCoord, crusher.balance));
		}
		else
			super.actionPerformed(button);
	}
}
