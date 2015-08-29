package cristaltek.hitekmod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCraftingTablet extends Slot {
	
	private InventoryCraftingTablet craftingMatrix;
    private EntityPlayer player;
    
    public SlotCraftingTablet(EntityPlayer player, InventoryCraftingTablet craftingMatrix, int slotIndex, int xPos, int yPos) {
        super(craftingMatrix, slotIndex, xPos, yPos);
        this.craftingMatrix = craftingMatrix;
        this.player = player;
    }
    
    @Override
    public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_) {
        super.onSlotChange(p_75220_1_, p_75220_2_);
        craftingMatrix.onGuiSaved(player);
    }

}
