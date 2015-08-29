package cristaltek.hitekmod.util;


import cristaltek.hitekmod.items.ItemCraftingTablet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CraftingTabletHelper {
	
	public static boolean playerHasCraftingTablet(EntityPlayer player) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            if (player.inventory.getStackInSlot(i).getItem() instanceof ItemCraftingTablet) {
                return true;
            }
        }
        return false;
    }
	
	public static ItemStack getCraftingTablet(EntityPlayer player) {
        ItemStack CraftingTablet = null;

        if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemCraftingTablet) {
            CraftingTablet = player.getHeldItem();
        } else {
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack stack = player.inventory.getStackInSlot(i);

                if (stack != null && stack.getItem() != null && stack.getItem() instanceof ItemCraftingTablet) {
                	CraftingTablet = player.inventory.getStackInSlot(i);
                }
            }
        }


        if (!player.worldObj.isRemote && CraftingTablet != null) {
            NBTHelper.setUUID(CraftingTablet);
        }

        return CraftingTablet;
    }

}
