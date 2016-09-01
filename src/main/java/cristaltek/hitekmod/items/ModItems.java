package cristaltek.hitekmod.items;

import cristaltek.hitekmod.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ModItems {
	// Items
	public static Item DarkEnergyIngot;

	// Tools
	public static Item TDTool;

	public static void init() {
		// Items
		DarkEnergyIngot = new ItemBase("DarkEnergyIngot");
		
		// Tools
		TDTool = new ItemTDTool("TemporalDecompositionTool");
	}
	
	public static void registerRenders() {
		registerRender(DarkEnergyIngot);
		registerRender(TDTool);
	}
	
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
