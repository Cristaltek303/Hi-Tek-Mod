package cristaltek.hitekmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.blocks.blockDarkstarBlock;
import cristaltek.hitekmod.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = "ht",name = "Hit-Tek Mod", version = "1.0")
public class HiTekMod {
	
	//Blocks
	public static Block blockDarkstarBlock;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		ModItems.init();
		
		//Blocks-------------------------------------------------------------------------------------------------------------------------------------------
		
		
		//Darkstar Block
		
		blockDarkstarBlock = new blockDarkstarBlock(Material.rock).setBlockName("blockDarkstarBlock").setBlockTextureName("ht:DarkStarBlock").setCreativeTab(tabHiTekMod);
		GameRegistry.registerBlock(blockDarkstarBlock, blockDarkstarBlock.getUnlocalizedName().substring(5));
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		//Proxy, TileEntity, entity, GUI and Registering
		
		//Recepies
		
		GameRegistry.addRecipe(new ItemStack(blockDarkstarBlock), new Object[]{"DDD","DDD","DDD", 'D', ModItems.itemDarkstar}); //Darkstar Block
		GameRegistry.addRecipe(new ItemStack(ModItems.itemImbuer), new Object[]{" D ","DAD"," D ", 'A', ModItems.itemDarkEnergyIngot, 'D', Items.diamond}); //Imbuer
		GameRegistry.addRecipe(new ItemStack(ModItems.itemConcentratedDarkstar), new Object[]{"DDD","DID","DDD", 'D', ModItems.itemDarkstar, 'I', ModItems.itemImbuer}); // Concentrated Darkstar
		GameRegistry.addRecipe(new ItemStack(ModItems.itemStaffHandle), new Object[]{" D "," H "," D ", 'D', ModItems.itemDarkstar, 'H', ModItems.itemHandle}); //StaffHandle
		GameRegistry.addRecipe(new ItemStack(ModItems.itemHandle), new Object[]{"  D"," D ","D  ", 'D', ModItems.itemDarkEnergyIngot,}); //Handle

		
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		//
	}
	
	public static CreativeTabs tabHiTekMod = new CreativeTabs("tabHiTekMod"){
		@Override
		public Item getTabIconItem(){
			return new ItemStack(ModItems.itemDarkstar).getItem();
		}
	};
}
