package handlers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.network.message.CraftingTabletMessage;
import cristaltek.hitekmod.network.message.PacketHandler;
import cristaltek.hitekmod.reference.GUIs;
import cristaltek.hitekmod.reference.Keybindings;
import cristaltek.hitekmod.util.CraftingTabletHelper;
import net.minecraft.entity.player.EntityPlayer;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

    public void init() {
        ClientRegistry.registerKeyBinding(Keybindings.openCraftingTablet);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
        if (Keybindings.openCraftingTablet.isPressed()) {
            EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();

            if (CraftingTabletHelper.getCraftingTablet(player) != null) {
                player.openGui(HiTekMod.instance, GUIs.CRAFTINGTABLET.ordinal(), player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
                PacketHandler.INSTANCE.sendToServer(new CraftingTabletMessage(CraftingTabletMessage.OPEN_CRAFTINGTABLET));
            }
        }
    }
}