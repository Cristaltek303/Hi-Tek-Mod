package cristaltek.hitekmod.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.client.gui.GuiCraftingTablet;
import cristaltek.hitekmod.common.CommonProxy;
import cristaltek.hitekmod.items.ModItems;
import cristaltek.hitekmod.items.rendering.ItemRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	
	
	@Override
	public void registerRenderInformation() {
		ItemRenderer itemRenderer = new ItemRenderer();
		
		MinecraftForgeClient.registerItemRenderer(ModItems.OPSword, itemRenderer);
		MinecraftForgeClient.registerItemRenderer(ModItems.TDTool, itemRenderer);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiCraftingTablet(player.inventory, world);
	}
}
