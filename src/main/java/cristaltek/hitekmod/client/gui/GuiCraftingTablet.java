package cristaltek.hitekmod.client.gui;

import org.lwjgl.opengl.GL11;

import com.sun.org.apache.xml.internal.security.encryption.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiCraftingTablet extends GuiScreen{

	
	int guiWidth = 220;
	int guiHeight = 193;
	
	@Override
	public void drawScreen(int x, int y, float ticks) {
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		GL11.glColor4f(1, 1, 1, 0.35F); //Alpha = Transparent
		drawDefaultBackground();
		mc.renderEngine.bindTexture(new ResourceLocation(cristaltek.hitekmod.HiTekMod.MOD_ID, "textures/gui/guiCraftingTablet.png"));
		drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
		
		super.drawScreen(x, y, ticks);
	}

}
