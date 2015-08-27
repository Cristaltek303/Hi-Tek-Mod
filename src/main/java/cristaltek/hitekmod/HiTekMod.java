package cristaltek.hitekmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cristaltek.hitekmod.blocks.ModBlocks;
import cristaltek.hitekmod.common.CommonProxy;
import cristaltek.hitekmod.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = HiTekMod.MOD_ID, name = HiTekMod.MOD_NAME, version = HiTekMod.MOD_VERSION)
public class HiTekMod {
	
	public static final String MOD_ID = "ht";
	public static final String MOD_NAME = "HiTek Mod";
	public static final String MOD_VERSION = "1.0";

	@Instance(MOD_ID)
	public static HiTekMod instance;

	@SidedProxy(clientSide = "cristaltek.hitekmod.client.ClientProxy", serverSide = "cristaltek.hitekmod.common.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		ModItems.init();
		ModBlocks.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(HiTekMod.instance, proxy);
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		//Proxy, TileEntity, entity, GUI and Registering
		
		
		//Recipes
		//Item Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.RawSilicon, 16), new Object[]{"A  ","B  ","C  ", 'A', Items.water_bucket, 'B', Blocks.sand, 'C', Blocks.dirt});
		GameRegistry.addSmelting(ModItems.RawSilicon, new ItemStack (ModItems.Silicon), 0.0F);
		GameRegistry.addRecipe(new ItemStack(ModItems.CircuitBoard), new Object[]{"ABA","BAB","ABA", 'A', Items.gold_nugget, 'B', ModItems.Silicon});
		GameRegistry.addRecipe(new ItemStack(ModItems.VIChip), new Object[]{"BAB","ACA","BAB", 'A', Items.gold_nugget, 'B', Items.redstone, 'C', ModItems.CircuitBoard});
		GameRegistry.addRecipe(new ItemStack(ModItems.VIStabilizer),new Object[]{"ABA","BCB","ABA", 'A', ModItems.darkEnergyIngot, 'B', ModItems.CircuitBoard, 'C', ModItems.VIChip});
		
		GameRegistry.addRecipe(new ItemStack(ModBlocks.darkstarBlock), new Object[]{"DDD","DDD","DDD", 'D', ModItems.darkstar}); //Darkstar Block
		GameRegistry.addRecipe(new ItemStack(ModItems.imbuer), new Object[]{" D ","DAD"," D ", 'A', ModItems.darkEnergyIngot, 'D', Items.diamond}); //Imbuer
		GameRegistry.addRecipe(new ItemStack(ModItems.concentratedDarkstar), new Object[]{"DDD","DID","DDD", 'D', ModItems.darkstar, 'I', ModItems.imbuer}); // Concentrated Darkstar
		GameRegistry.addRecipe(new ItemStack(ModItems.staffHandle), new Object[]{" D "," H "," D ", 'D', ModItems.darkstar, 'H', ModItems.handle}); //StaffHandle
		GameRegistry.addRecipe(new ItemStack(ModItems.handle), new Object[]{"  D"," D ","D  ", 'D', ModItems.darkEnergyIngot,}); //Handle
		GameRegistry.addRecipe(new ItemStack(ModItems.CraftingChip), new Object[]{"ABA","BCB","ABA", 'A', Items.redstone, 'B', Blocks.crafting_table, 'C', ModItems.darkEnergyIngot});//Crafting Chip
		//Armor Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.htHelmet), new Object[]{"BAB","ACA","   ", 'A', ModItems.concentratedDarkstar, 'B', ModItems.darkEnergyIngot, 'C', Items.diamond_helmet});
		GameRegistry.addRecipe(new ItemStack(ModItems.htChestplate), new Object[]{"BCB","ABA","BAB", 'A', ModItems.concentratedDarkstar, 'B', ModItems.darkEnergyIngot, 'C', Items.diamond_chestplate});
		GameRegistry.addRecipe(new ItemStack(ModItems.htLeggings), new Object[]{"BAB","ACA","B B", 'A', ModItems.concentratedDarkstar, 'B', ModItems.darkEnergyIngot, 'C', Items.diamond_leggings});
		GameRegistry.addRecipe(new ItemStack(ModItems.htBoots), new Object[]{"   ","ACA","B B", 'A', ModItems.concentratedDarkstar, 'B', ModItems.darkEnergyIngot, 'C', Items.diamond_boots});
		//Tool Recipes
		GameRegistry.addRecipe(new ItemStack(ModItems.CraftingTablet),new Object[]{"CCC","DAD","BBB", 'A', ModItems.CraftingChip, 'B', ModItems.darkEnergyIngot, 'C', Blocks.glass_pane, 'D', Blocks.stone_button});//CraftingTablet
		GameRegistry.addRecipe(new ItemStack(ModItems.TDTool),new Object[]{"AAA","ABA"," B ",'A', ModItems.concentratedDarkstar, 'B', ModItems.staffHandle});//TDTool
		GameRegistry.addRecipe(new ItemStack(ModItems.OPSword),new Object[]{" A "," A ","CBC", 'A', ModItems.concentratedDarkstar, 'B', ModItems.handle, 'C', ModItems.darkEnergyIngot});//OPSword 

		proxy.registerRenderInformation();
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
