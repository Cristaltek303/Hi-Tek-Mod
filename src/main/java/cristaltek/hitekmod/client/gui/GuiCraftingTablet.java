package cristaltek.hitekmod.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.client.gui.button.CraftingTabletButton;
import cristaltek.hitekmod.client.inventory.ContainerCraftingTablet;
import cristaltek.hitekmod.network.PacketHandler;
import cristaltek.hitekmod.network.message.CraftingTabletMessage;
import cristaltek.hitekmod.reference.Textures;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

@SideOnly(Side.CLIENT)
public class GuiCraftingTablet extends GuiContainer {

	private CraftingTabletButton balanceButton;
	private CraftingTabletButton spinButton;
	private CraftingTabletButton clearButton;
	
	public GuiCraftingTablet(Container container) {
		super(container);
		xSize = 220;
		ySize = 193;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		
		int xStart = ((width - xSize) / 2);
		int yStart = (height - ySize) / 2;
		
		this.buttonList.add(this.balanceButton = new CraftingTabletButton(1, xStart + 30, yStart + 26, 13, 13, CraftingTabletButton.BALANCE));
		this.buttonList.add(this.spinButton = new CraftingTabletButton(2, xStart + 30, yStart + 42, 13, 13, CraftingTabletButton.SPIN));
		this.buttonList.add(this.clearButton = new CraftingTabletButton(3, xStart + 30, yStart + 58, 13, 13, CraftingTabletButton.EMPTY));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float ticks, int x, int y) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F); //Alpha = Transparent
		mc.getTextureManager().bindTexture(Textures.Gui.CRAFTING_TABLET);
		
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (this.inventorySlots instanceof ContainerCraftingTablet) {
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
	}
}
