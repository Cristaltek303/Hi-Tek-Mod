package cristaltek.hitekmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.blocks.ModBlocks;
import cristaltek.hitekmod.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = "ht",name = "Hit-Tek Mod", version = "1.0")
public class HiTekMod {
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		ModItems.init();
		ModBlocks.init();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		//Proxy, TileEntity, entity, GUI and Registering
		
		//Recepies
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.darkstarBlock), new Object[]{"DDD","DDD","DDD", 'D', ModItems.darkstar}); //Darkstar Block
		GameRegistry.addRecipe(new ItemStack(ModItems.imbuer), new Object[]{" D ","DAD"," D ", 'A', ModItems.darkEnergyIngot, 'D', Items.diamond}); //Imbuer
		GameRegistry.addRecipe(new ItemStack(ModItems.concentratedDarkstar), new Object[]{"DDD","DID","DDD", 'D', ModItems.darkstar, 'I', ModItems.imbuer}); // Concentrated Darkstar
		GameRegistry.addRecipe(new ItemStack(ModItems.staffHandle), new Object[]{" D "," H "," D ", 'D', ModItems.darkstar, 'H', ModItems.handle}); //StaffHandle
		GameRegistry.addRecipe(new ItemStack(ModItems.handle), new Object[]{"  D"," D ","D  ", 'D', ModItems.darkEnergyIngot,}); //Handle
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event) {
		//
	}
	
	public static CreativeTabs tabHiTekMod = new CreativeTabs("tabHiTekMod"){
		@Override
		public Item getTabIconItem(){
			return new ItemStack(ModItems.darkstar).getItem();
		}
	};
}
