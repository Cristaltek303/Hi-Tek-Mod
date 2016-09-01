package cristaltek.hitekmod;

import cristaltek.hitekmod.blocks.ModBlocks;
import cristaltek.hitekmod.items.ModItems;
import cristaltek.hitekmod.proxy.ServerProxy;
import cristaltek.hitekmod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class HiTekMod {
	
	@SidedProxy (clientSide = "cristaltek.hitekmod.proxy.ClientProxy", serverSide = "cristaltek.hitekmod.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configs.init(new Configuration(event.getSuggestedConfigurationFile()));
		ModItems.init();
		ModBlocks.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModItems.registerRenders();
		ModBlocks.registerRenders();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	public static CreativeTabs tabHiTekMod = new CreativeTabs(Reference.MOD_NAME) {
		@Override
		public Item getTabIconItem() {
			return ModItems.TDTool;
		}
	};
}
