package cristaltek.hitekmod.client.gui;

import cristaltek.hitekmod.client.gui.button.TabButton;
import cristaltek.hitekmod.inventory.ContainerCraftingTablet;
import cristaltek.hitekmod.network.PacketHandler;
import cristaltek.hitekmod.network.message.CraftingTabletMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public abstract class GuiConfigurable extends GuiContainer
{
	private TabButton configTab;
	private TabButton backTab;
	
	public GuiConfigurable(Container container)
	{
		super(container);
		xSize = 220;
		ySize = 193;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		super.initGui();
		
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
		buttonList.add(configTab = new TabButton(xStart + 220, yStart + 10, 20, 21, 1, TabButton.CONFIG));
		buttonList.add(backTab = new TabButton(xStart + 220, yStart + 35, 20, 21, 1, TabButton.BACK));
		backTab.visible = false;
	}

	@Override
	protected abstract void drawGuiContainerBackgroundLayer(float ticks, int x, int y);

/*	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (this.inventorySlots instanceof ContainerCraftingTablet)
		{
			ContainerCraftingTablet container = (ContainerCraftingTablet)this.inventorySlots;
			
			if (button == balanceButton) {
				container.balanceMatrix();
				PacketHandler.INSTANCE.sendToServer(new CraftingTabletMessage(CraftingTabletMessage.BALANCE_MATRIX));
			}
			else if (button == spinButton) {
				container.spinMatrix();
				PacketHandler.INSTANCE.sendToServer(new CraftingTabletMessage(CraftingTabletMessage.SPIN_MATRIX));
			}
			else if (button == clearButton) {
				container.clearMatrix();
				PacketHandler.INSTANCE.sendToServer(new CraftingTabletMessage(CraftingTabletMessage.CLEAR_MATRIX));
			}
		}
	}*/
}
