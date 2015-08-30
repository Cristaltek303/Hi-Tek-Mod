package cristaltek.hitekmod;

import cristaltek.hitekmod.items.ItemCraftingTablet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingTabletHelper {

    public static boolean playerHasIBench(EntityPlayer player) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            if (player.inventory.getStackInSlot(i).getItem() instanceof ItemCraftingTablet) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getIBench(EntityPlayer player) {
        ItemStack iBench = null;

        if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemCraftingTablet) {
            iBench = player.getHeldItem();
        } else {
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack stack = player.inventory.getStackInSlot(i);

                if (stack != null && stack.getItem() != null && stack.getItem() instanceof ItemCraftingTablet) {
                    iBench = player.inventory.getStackInSlot(i);
                }
            }
        }


        if (!player.worldObj.isRemote && iBench != null) {
            NBTHelper.setUUID(iBench);
        }

        return iBench;
    }
}
