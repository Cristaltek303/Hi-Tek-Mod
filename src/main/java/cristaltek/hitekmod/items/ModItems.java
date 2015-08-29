package cristaltek.hitekmod.items;

import net.minecraft.item.Item;

public class ModItems {
	//Items
	public static Item darkstar;
	public static Item concentratedDarkstar;
	public static Item staffHandle;
	public static Item imbuer;
	public static Item handle;
	public static Item darkEnergyIngot;
	public static Item craftingChip;
	
	//Items for Machine crafting
	public static Item RawSilicon;
	public static Item Silicon;
	public static Item CircuitBoard;
	public static Item VIChip;
	public static Item VIStabilizer;// ==> Machine Frame
	
	//Tools/Weapons
	public static Item TDTool; //WIP
	public static Item OPSword; //WIP
	public static Item CraftingTablet; //WIP
	
	//Armor
	public static Item htHelmet; //WIP
	public static Item htChestplate; //WIP
	public static Item htLeggings; //WIP
	public static Item htBoots; //WIP

	public static void init() {
		//Items
		darkstar = new ItemBase("darkstar");
		concentratedDarkstar = new ItemBase("concentratedDarkstar");
		staffHandle = new ItemBase("staffHandle");
		imbuer = new ItemBase("imbuer");
		handle = new ItemBase("handle");
		darkEnergyIngot = new ItemBase("darkEnergyIngot");
		craftingChip = new ItemBase("CraftingChip");
		CircuitBoard = new ItemBase("CircuitBoard");
		RawSilicon = new ItemBase("RawSilicon");
		Silicon = new ItemBase("Silicon");
		VIChip = new ItemBase("VIChip");
		VIStabilizer = new ItemBase("VIStabilizer");
		
		//Tool/Weapons
		TDTool = new ItemTDTool("temporalDecompositionTool");
		OPSword = new ItemOPSword("OPSword");
		CraftingTablet = new ItemCraftingTablet("CraftingTablet");
		
		//Armor
		htHelmet = new ItemhtArmor("htHelmet", 0);
		htChestplate = new ItemhtArmor("htChestplate", 1);
		htLeggings = new ItemhtArmor("htLeggings", 2);
		htBoots = new ItemhtArmor("htBoots", 3);
	}
}
