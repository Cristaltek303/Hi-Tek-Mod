package cristaltek.hitekmod.client.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerCraftingTablet extends Container {

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
	public IInventory craftResult = new InventoryCraftResult();
	private World worldObj;

	public ContainerCraftingTablet(InventoryPlayer inventory, World world) {
		this.worldObj = world;

		this.addSlotToContainer(new SlotCrafting(inventory.player, this.craftMatrix, this.craftResult, 0, 166, 41));

		int i, j;

		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 3; ++j) {
				this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 59 + j * 22, 19 + i * 22));
			}
		}

		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 18 + j * 21, 98 + i * 21));
			}
		}

		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(inventory, i, 18 + i * 21, 165));
		}

		this.onCraftMatrixChanged(this.craftMatrix);
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!this.worldObj.isRemote) {
			for (int i = 0; i < 9; ++i) {
				ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					player.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotId);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (slotId == 0) {
				if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			else if (slotId >= 10 && slotId < 37) {
				if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
					return null;
				}
			}
			else if (slotId >= 37 && slotId < 46) {
				if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
					return null;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack)null);
			}
			else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean func_94530_a(ItemStack itemstack, Slot slot) {
		return slot.inventory != this.craftResult && super.func_94530_a(itemstack, slot);
	}
}
