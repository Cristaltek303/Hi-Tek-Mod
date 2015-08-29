package cristaltek.hitekmod.plugins.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.DefaultOverlayHandler;
import cristaltek.hitekmod.client.gui.GuiCraftingTablet;
import cristaltek.hitekmod.reference.Reference;

public class NEICraftingTabletConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		API.registerGuiOverlay(GuiCraftingTablet.class, "crafting", new CraftingTabletStackPositioner());
		API.registerGuiOverlayHandler(GuiCraftingTablet.class, new DefaultOverlayHandler(17, 11), "crafting");
	}
	
	@Override
	public String getName() {
		return Reference.MOD_ID;
	}
	
	@Override
	public String getVersion() {
		return Reference.MOD_VERSION;
	}
}
