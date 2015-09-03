package cristaltek.hitekmod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryTrash implements IInventory {

	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return null;
	}
	
	@Override
	public ItemStack decrStackSize(int index, int amount) {
		return null;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		return null;
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack itemstack) {
	}
	
	@Override
	public String getInventoryName() {
		return null;
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public void markDirty() {
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}
	
	@Override
	public void openInventory() {
	}
	
	@Override
	public void closeInventory() {
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack itemstack) {
		return true;
	}
}
