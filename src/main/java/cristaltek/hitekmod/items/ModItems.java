package cristaltek.hitekmod.items;

import cristaltek.hitekmod.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ModItems {
	// Items
	public static Item DarkEnergyIngot;
	public static Item CircuitBoard;
	public static Item ConcentratedDarkstar;
	public static Item CraftingChip;
	public static Item Darkstar;
	public static Item Handle;
	public static Item Imbuer;
	public static Item InfusedGoldIngot;
	public static Item RawSilicon;
	public static Item Silicon;
	public static Item StaffHandle;
	public static Item VIChip;
	public static Item VIStabilizer;


	// Tools
	public static Item TDTool;
	public static Item Magnet;
	public static Item VCSword;

	public static void init() {
		// Items
		DarkEnergyIngot = new ItemBase("DarkEnergyIngot");
		CircuitBoard = new ItemBase("CircuitBoard");
		ConcentratedDarkstar = new ItemBase("ConcentratedDarkstar");
		CraftingChip = new ItemBase("CraftingChip");
		Darkstar = new ItemBase("Darkstar");
		Handle = new ItemBase("Handle");
		Imbuer = new ItemBase("Imbuer");
		InfusedGoldIngot = new ItemBase("InfusedGoldIngot");
		RawSilicon = new ItemBase("RawSilicon");
		Silicon = new ItemBase("Silicon");
		StaffHandle = new ItemBase("StaffHandle");
		VIChip = new ItemBase("VIChip");
		VIStabilizer = new ItemBase("VIStabilizer");

		
		// Tools
		TDTool = new ItemTDTool("TemporalDecompositionTool");
		Magnet = new ItemMagnet("Magnet");
		VCSword = new ItemVCSword("VoidCarver");
	}
	
	public static void registerRenders() {
		// Items
		registerRender(DarkEnergyIngot);
		registerRender(CircuitBoard);
		registerRender(ConcentratedDarkstar);
		registerRender(CraftingChip);
		registerRender(Darkstar);
		registerRender(Handle);
		registerRender(Imbuer);
		registerRender(InfusedGoldIngot);
		registerRender(RawSilicon);
		registerRender(Silicon);
		registerRender(StaffHandle);
		registerRender(VIChip);
		registerRender(VIStabilizer);

		
		//Tools
		registerRender(TDTool);
		registerRender(Magnet);
		registerRender(VCSword);
	}
	
	private static void registerRender(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
