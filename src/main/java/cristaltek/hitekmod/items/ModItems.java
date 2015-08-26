package cristaltek.hitekmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {
	
	//Items
	public static Item darkstar;
	public static Item concentratedDarkstar;
	public static Item staffHandle;
	public static Item imbuer;
	public static Item handle;
	public static Item darkEnergyIngot;
	public static Item CraftingChip;
	
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
		CraftingChip =new ItemBase("CraftingChip");

		
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
