package cristaltek.hitekmod.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cristaltek.hitekmod.network.message.CraftingTabletMessage;
import cristaltek.hitekmod.reference.Reference;

public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

	public static void init()
	{
		INSTANCE.registerMessage(CraftingTabletMessage.Handler.class, CraftingTabletMessage.class, 0, Side.SERVER);
	}
}
