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
	
	//Tools/Weapons
	public static Item TDTool;
	
	//Armor
	public static Item htHelmet;
	public static Item htChestplate;
	public static Item htLeggings;
	public static Item htBoots;

	public static void init() {
		
		//Items
		
		darkstar = new ItemBase("darkstar");
		concentratedDarkstar = new ItemBase("concentratedDarkstar");
		staffHandle = new ItemBase("staffHandle");
		imbuer = new ItemBase("imbuer");
		handle = new ItemBase("handle");
		darkEnergyIngot = new ItemBase("darkEnergyIngot");
		
		//Tool/Weapons
        
		TDTool = new ItemTDTool("temporalDecompositionTool");
		
		//Armor
		htHelmet = new ItemhtArmor("htHelmet", 0);
		htChestplate = new ItemhtArmor("htChestplate", 1);
     	htLeggings = new ItemhtArmor("htLeggings", 2);
		htBoots = new ItemhtArmor("htBoots", 3);
	}
}
