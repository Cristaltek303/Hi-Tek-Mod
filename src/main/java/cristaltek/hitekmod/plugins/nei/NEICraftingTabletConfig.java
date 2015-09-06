package cristaltek.hitekmod.plugins.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import cristaltek.hitekmod.client.gui.GuiCraftingTablet;
import cristaltek.hitekmod.reference.Reference;

public class NEICraftingTabletConfig implements IConfigureNEI
{

	@Override
	public void loadConfig()
	{
		API.registerGuiOverlay(GuiCraftingTablet.class, "crafting", new PreciseStackPositioner(59, 19, 22, 22));
		API.registerGuiOverlayHandler(GuiCraftingTablet.class, new PreciseOverlayHandler(59, 19, 22, 22), "crafting");
	}
	
	@Override
	public String getName()
	{
		return Reference.MOD_ID;
	}
	
	@Override
	public String getVersion()
	{
		return Reference.MOD_VERSION;
	}
}
