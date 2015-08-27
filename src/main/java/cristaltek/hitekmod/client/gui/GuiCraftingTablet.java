package cristaltek.hitekmod.client.gui;

import org.lwjgl.opengl.GL11;

import cristaltek.hitekmod.client.inventory.ContainerCraftingTablet;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiCraftingTablet extends GuiContainer {

	public GuiCraftingTablet(InventoryPlayer inventory, World world) {
		super(new ContainerCraftingTablet(inventory, world));
		xSize = 220;
		ySize = 193;
	}
	
//	@Override
//	public void drawScreen(int x, int y, float ticks) {
//		int guiX = (width - guiWidth) / 2;
//		int guiY = (height - guiHeight) / 2;
//		GL11.glColor4f(1, 1, 1, 0.85F); //Alpha = Transparent
//		//drawDefaultBackground();
//		mc.renderEngine.bindTexture(new ResourceLocation(cristaltek.hitekmod.HiTekMod.MOD_ID, "textures/gui/guiCraftingTablet.png"));
//		drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
//		
//		super.drawScreen(x, y, ticks);
//	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float ticks, int x, int y) {
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;
		GL11.glColor4f(1, 1, 1, 0.85F); //Alpha = Transparent
		//drawDefaultBackground();
		mc.renderEngine.bindTexture(new ResourceLocation(cristaltek.hitekmod.HiTekMod.MOD_ID, "textures/gui/guiCraftingTablet.png"));
		drawTexturedModalRect(guiX, guiY, 0, 0, xSize, ySize);
	}
}
