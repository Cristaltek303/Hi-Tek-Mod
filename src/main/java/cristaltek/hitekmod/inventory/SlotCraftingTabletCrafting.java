package cristaltek.hitekmod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class SlotCraftingTabletCrafting extends SlotCrafting {
	
	private EntityPlayer player;
    private InventoryCraftingTablet craftingMatrix;

    public SlotCraftingTabletCrafting(EntityPlayer player, InventoryCraftingTablet craftingMatrix, IInventory result, int slotIndex, int xPos, int yPos) {
        super(player, craftingMatrix, result, slotIndex, xPos, yPos);
        this.player = player;
        this.craftingMatrix = craftingMatrix;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer entityPlayer, ItemStack crafted) {
//        System.out.println("Save recipe for" + crafted);
//        for (int i=0; i < craftingMatrix.inventory.length; i++)
//            System.out.println(craftingMatrix.inventory[i]);

        super.onPickupFromSlot(entityPlayer, crafted);
    }

}
