package cristaltek.hitekmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.item.itemTDTool;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = "ht",name = "Hit-Tek Mod", version = "1.0")
public class HiTekMod {
	
	//Items
	public static Item itemDarkstar;
	public static Item itemConcentratedDarkstar;
	public static Item itemStaffHandle;
	public static Item itemImbuer;
	public static Item itemHandle;
	public static Item itemDarkEnergyIngot;
	
	//Blocks
	public static Block blockDarkstarBlock;
	
	//Tools/Weapons
	public static Item TDTool;
	
	//Tool/Weapons Materials
	public static final Item.ToolMaterial TDToolMaterial = EnumHelper.addToolMaterial("TDToolMaterial", 4, -1, 10000.0F, 1.0F, 35);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//Item/Block init and registration
		//Config Handling
		
		
		//Items-------------------------------------------------------------------------------------------------------------------------------------------
		
		//Darkstar
		itemDarkstar = new itemDarkstar().setUnlocalizedName("itemDarkstar").setTextureName("ht:Darkstar").setCreativeTab(tabHiTekMod);
		GameRegistry.registerItem(itemDarkstar, itemDarkstar.getUnlocalizedName().substring(5));
		
		//ConcentratedDarkstar
		itemConcentratedDarkstar = new itemConcentratedDarkstar().setUnlocalizedName("itemConcentratedDarkstar").setTextureName("ht:ConcentratedDarkstar").setCreativeTab(tabHiTekMod);
		GameRegistry.registerItem(itemConcentratedDarkstar, itemConcentratedDarkstar.getUnlocalizedName().substring(5));
		
		//StaffHandle
		itemStaffHandle = new itemStaffHandle().setUnlocalizedName("itemStaffHandle").setTextureName("ht:StaffHandle").setCreativeTab(tabHiTekMod);
		GameRegistry.registerItem(itemStaffHandle, itemStaffHandle.getUnlocalizedName().substring(5));
		
		//Imbuer
		itemImbuer = new itemImbuer().setUnlocalizedName("itemImbuer").setTextureName("ht:Imbuer").setCreativeTab(tabHiTekMod);
		GameRegistry.registerItem(itemImbuer, itemImbuer.getUnlocalizedName().substring(5));
		
		//Handle
		itemHandle = new itemHandle().setUnlocalizedName("itemHandle").setTextureName("ht:Handle").setCreativeTab(tabHiTekMod);
		GameRegistry.registerItem(itemHandle, itemHandle.getUnlocalizedName().substring(5));
		
		//Dark Energy Ingot
		itemDarkEnergyIngot = new itemDarkEnergyIngot().setUnlocalizedName("itemDarkEnergyIngot").setTextureName("ht:DarkEnergyIngot").setCreativeTab(tabHiTekMod);
		GameRegistry.registerItem(itemDarkEnergyIngot, itemDarkEnergyIngot.getUnlocalizedName().substring(5));
		
		
		
		//Blocks-------------------------------------------------------------------------------------------------------------------------------------------
		
		
		//Darkstar Block
		
		blockDarkstarBlock = new blockDarkstarBlock(Material.rock).setBlockName("blockDarkstarBlock").setBlockTextureName("ht:DarkStarBlock").setCreativeTab(tabHiTekMod);
		GameRegistry.registerBlock(blockDarkstarBlock, blockDarkstarBlock.getUnlocalizedName().substring(5));
		
		
		
		//Tool/Weapons-------------------------------------------------------------------------------------------------------------------------------------------
        
		TDTool = new itemTDTool(TDToolMaterial).setUnlocalizedName("itemTDTool").setTextureName("ht:TemporalDecompositionTool").setCreativeTab(tabHiTekMod);
		GameRegistry.registerItem(TDTool, TDTool.getUnlocalizedName().substring(5));
		
		
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		//Proxy, TileEntity, entity, GUI and Registering
		
		//Recepies
		
		GameRegistry.addRecipe(new ItemStack(blockDarkstarBlock), new Object[]{"DDD","DDD","DDD", 'D', HiTekMod.itemDarkstar}); //Darkstar Block
		GameRegistry.addRecipe(new ItemStack(itemImbuer), new Object[]{" D ","DAD"," D ", 'A', HiTekMod.itemDarkEnergyIngot, 'D', Items.diamond}); //Imbuer
		GameRegistry.addRecipe(new ItemStack(itemConcentratedDarkstar), new Object[]{"DDD","DID","DDD", 'D', HiTekMod.itemDarkstar, 'I', HiTekMod.itemImbuer}); // Concentrated Darkstar
		GameRegistry.addRecipe(new ItemStack(itemStaffHandle), new Object[]{" D "," H "," D ", 'D', HiTekMod.itemDarkstar, 'H', HiTekMod.itemHandle}); //StaffHandle
		GameRegistry.addRecipe(new ItemStack(itemHandle), new Object[]{"  D"," D ","D  ", 'D', HiTekMod.itemDarkEnergyIngot,}); //Handle

		
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		//
	}
	
	public static CreativeTabs tabHiTekMod = new CreativeTabs("tabHiTekMod"){
		@Override
		public Item getTabIconItem(){
			return new ItemStack(itemDarkstar).getItem();
		}
	};
}
