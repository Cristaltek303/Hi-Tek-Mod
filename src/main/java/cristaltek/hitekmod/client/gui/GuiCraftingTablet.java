package cristaltek.hitekmod.client.gui;

import org.lwjgl.opengl.GL11;

import cristaltek.hitekmod.HiTekMod;
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

	@Override
	protected void drawGuiContainerBackgroundLayer(float ticks, int x, int y) {
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F); //Alpha = Transparent
		mc.getTextureManager().bindTexture(new ResourceLocation(HiTekMod.MOD_ID, "textures/gui/guiCraftingTablet.png"));
		drawTexturedModalRect(guiX, guiY, 0, 0, xSize, ySize);
	}
}
