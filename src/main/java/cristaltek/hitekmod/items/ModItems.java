package cristaltek.hitekmod.items;

import net.minecraft.item.Item;

public class ModItems
{
	//Items
	public static Item Darkstar;
	public static Item ConcentratedDarkstar;
	public static Item StaffHandle;
	public static Item Imbuer;
	public static Item Handle;
	public static Item DarkEnergyIngot;
	public static Item CraftingChip;
	
	//Items for Machine crafting
	public static Item RawSilicon;
	public static Item Silicon;
	public static Item CircuitBoard;
	public static Item VIChip;
	public static Item VIStabilizer;// ==> Machine Frame
	
	//Tools/Weapons
	public static Item TDTool;
	public static Item OPSword; //WIP
	public static Item CraftingTablet;
	public static Item Magnet;
	
	//Armor
	public static Item htHelmet; //WIP
	public static Item htChestplate; //WIP
	public static Item htLeggings; //WIP
	public static Item htBoots; //WIP

	public static void init()
	{
		//Items
		Darkstar = new ItemBase("Darkstar");
		ConcentratedDarkstar = new ItemBase("ConcentratedDarkstar");
		StaffHandle = new ItemBase("StaffHandle");
		Imbuer = new ItemBase("Imbuer");
		Handle = new ItemBase("Handle");
		DarkEnergyIngot = new ItemBase("DarkEnergyIngot");
		CraftingChip = new ItemBase("CraftingChip");
		CircuitBoard = new ItemBase("CircuitBoard");
		RawSilicon = new ItemBase("RawSilicon");
		Silicon = new ItemBase("Silicon");
		VIChip = new ItemBase("VIChip");
		VIStabilizer = new ItemBase("VIStabilizer");
		
		//Tool/Weapons
		TDTool = new ItemTDTool("TemporalDecompositionTool");
		OPSword = new ItemOPSword("OPSword");
		CraftingTablet = new ItemCraftingTablet("CraftingTablet");
		Magnet = new ItemMagnet("Magnet");
		
		//Armor
		htHelmet = new ItemhtArmor("htHelmet", 0);
		htChestplate = new ItemhtArmor("htChestplate", 1);
		htLeggings = new ItemhtArmor("htLeggings", 2);
		htBoots = new ItemhtArmor("htBoots", 3);
	}
}
