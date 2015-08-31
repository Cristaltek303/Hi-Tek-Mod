package cristaltek.hitekmod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cristaltek.hitekmod.blocks.ModBlocks;
import cristaltek.hitekmod.common.CommonProxy;
import cristaltek.hitekmod.handlers.GuiHandler;
import cristaltek.hitekmod.items.ModItems;
import cristaltek.hitekmod.network.PacketHandler;
import cristaltek.hitekmod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class HiTekMod {

	@Instance(Reference.MOD_ID)
	public static HiTekMod instance;
	
	@SidedProxy(clientSide = "cristaltek.hitekmod.client.ClientProxy", serverSide = "cristaltek.hitekmod.common.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		configs.init(new Configuration(event.getSuggestedConfigurationFile()));
		PacketHandler.init();
		ModItems.init();
		ModBlocks.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(HiTekMod.instance, new GuiHandler());
		
		Recipes.init();
		
		proxy.registerRenderInformation();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
	
	public static CreativeTabs tabHiTekMod = new CreativeTabs(Reference.MOD_NAME) {
		@Override
		public Item getTabIconItem() {
			return ModItems.darkstar;
		}
	};
}
