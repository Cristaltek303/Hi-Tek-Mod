package cristaltek.hitekmod.reference;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(Side.CLIENT)
public class Keybindings {
    public static KeyBinding openCraftingTablet = new KeyBinding("key.open_craftingtablet.desc", Keyboard.KEY_C, "iBench");
}