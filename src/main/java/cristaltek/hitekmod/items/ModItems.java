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

	public static void init() {
		
		//Items-------------------------------------------------------------------------------------------------------------------------------------------
		
		darkstar = new ItemBase("darkstar");
		concentratedDarkstar = new ItemBase("concentratedDarkstar");
		staffHandle = new ItemBase("staffHandle");
		imbuer = new ItemBase("imbuer");
		handle = new ItemBase("handle");
		darkEnergyIngot = new ItemBase("darkEnergyIngot");
		
		//Tool/Weapons-------------------------------------------------------------------------------------------------------------------------------------------
        
		TDTool = new ItemTDTool("temporalDecompositionTool");
	}
}
