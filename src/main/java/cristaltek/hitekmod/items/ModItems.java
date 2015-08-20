package cristaltek.hitekmod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {
	
	//Items
	public static Item itemDarkstar;
	public static Item itemConcentratedDarkstar;
	public static Item itemStaffHandle;
	public static Item itemImbuer;
	public static Item itemHandle;
	public static Item itemDarkEnergyIngot;
	
	//Tools/Weapons
	public static Item TDTool;
	
	//Tool/Weapons Materials
	public static final ToolMaterial TDToolMaterial = EnumHelper.addToolMaterial("TDToolMaterial", 4, -1, 10000.0F, 1.0F, 35);

	public static void init() {
		
		//Items-------------------------------------------------------------------------------------------------------------------------------------------
		
		itemDarkstar = new ItemBase("darkstar");
		itemConcentratedDarkstar = new ItemBase("concentratedDarkstar");
		itemStaffHandle = new ItemBase("staffHandle");
		itemImbuer = new ItemBase("imbuer");
		itemHandle = new ItemBase("handle");
		itemDarkEnergyIngot = new ItemBase("darkEnergyIngot");
		
		//Tool/Weapons-------------------------------------------------------------------------------------------------------------------------------------------
        
		//Temporal Decomposition Tool
		TDTool = new itemTDTool(TDToolMaterial).setUnlocalizedName("itemTDTool").setTextureName("ht:TemporalDecompositionTool").setCreativeTab(HiTekMod.tabHiTekMod);
		GameRegistry.registerItem(TDTool, TDTool.getUnlocalizedName().substring(5));
	}
}
