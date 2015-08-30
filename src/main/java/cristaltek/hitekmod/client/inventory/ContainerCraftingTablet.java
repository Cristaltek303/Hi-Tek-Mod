package cristaltek.hitekmod.client.inventory;

import java.util.ArrayList;

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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ContainerCraftingTablet extends Container {

	private EntityPlayer player;
	private World world;
	
	public InventoryCraftingTablet craftingMatrix;
	public InventoryCraftResult craftingResult;
	public InventoryTrash trash;
	
	public ContainerCraftingTablet(EntityPlayer player, InventoryCraftingTablet craftingTabletInventory) {
		this.player = player;
		this.world = player.worldObj;
		
		this.craftingMatrix = craftingTabletInventory;
		this.craftingMatrix.setEventHandler(this);
		this.craftingResult = new InventoryCraftResult();
		
		this.trash = new InventoryTrash();
		
		this.addSlotToContainer(new SlotCrafting(player, this.craftingMatrix, this.craftingResult, 0, 166, 41));
		
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				this.addSlotToContainer(new Slot(this.craftingMatrix, j + i * 3, 59 + j * 22, 19 + i * 22));
			}
		}
		
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 18 + j * 21, 98 + i * 21));
			}
		}
		
		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 18 + i * 21, 165));
		}
		
		this.addSlotToContainer(new Slot(this.trash, 0, 188, 19));
		
		this.onCraftMatrixChanged(this.craftingMatrix);
		
//		this.readFromNBT(this.itemStack.getTagCompound());
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		this.craftingResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftingMatrix, this.world));
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		
		if (!player.worldObj.isRemote) {
/*			NBTTagCompound nbtTagCompound = this.itemStack.getTagCompound();
			if (nbtTagCompound == null)
				nbtTagCompound = new NBTTagCompound();
			this.writeToNBT(nbtTagCompound);
			this.itemStack.setTagCompound(nbtTagCompound);*/
			this.craftingMatrix.onGuiSaved(player);
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
	
/*	@Override
	public boolean func_94530_a(ItemStack itemstack, Slot slot) {
		return slot.inventory != this.craftingResult && super.func_94530_a(itemstack, slot);
	}*/

	public void balanceMatrix() {
        boolean[] balancedSlots = new boolean[9];

        // Go through all the slots, if the stack in the slots matches stacks in any other slots, split the total between all matching slots
        for (int i = 0; i < 9; i++) {
            ItemStack currentStack = craftingMatrix.getStackInSlot(i);
            ArrayList<Integer> matchingSlotIndexes = new ArrayList<Integer>();

            // This stack has not been balanced yet
            if (balancedSlots[i] == false && currentStack != null && currentStack.isStackable()) {
                int matchingStacks = 1;
                int totalItems = currentStack.stackSize;
                matchingSlotIndexes.add(i);

                // It is possible to balance this stack if other stacks are the same type, ignore previous currentStacks
                for (int j = i + 1; j < 9; j++) {
                    // Now we find how many stacks are stackable with the current one
                    ItemStack tStack = craftingMatrix.getStackInSlot(j);

                    if (tStack != null && currentStack != null && currentStack.getItem() == tStack.getItem()) {
                        matchingSlotIndexes.add(j);
                        matchingStacks++;
                        totalItems += tStack.stackSize;
                        balancedSlots[j] = true;
                    }
                }

                int balancedItemSize = totalItems / matchingStacks;
                int remainingItemSize = totalItems % matchingStacks;

                for (Integer index : matchingSlotIndexes) {
                    craftingMatrix.getStackInSlot(index).stackSize = balancedItemSize;
                    if (remainingItemSize > 0) {
                        craftingMatrix.getStackInSlot(index).stackSize += 1;
                        remainingItemSize--;
                    }
                }

                balancedSlots[i] = true;
            }
        }
	}

	public void spinMatrix() {
        ArrayList<ItemStack> tempStacks = new ArrayList<ItemStack>(9);
        for (int i = 0; i < 9; i++) {
            tempStacks.add(craftingMatrix.getStackInSlot(i));
        }

        int[] original = new int[]{0, 1, 2, 3, 5, 6, 7, 8};
        int[] rotated;

        rotated = new int[]{3, 0, 1, 6, 2, 7, 8, 5};

        for (int i = 0; i < original.length; i++) {
            craftingMatrix.setInventorySlotContents(original[i], tempStacks.get(rotated[i]));
        }
	}

	public void clearMatrix() {
        for (int i = 1; i <= 9; i++) {
            transferStackInSlot(player, i);
        }
	}
	
/*	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		if (nbtTagCompound != null) {
		NBTTagList tagList = nbtTagCompound.getTagList("Items", 10);
		
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound stackTag = tagList.getCompoundTagAt(i);
			byte slot = stackTag.getByte("Slot");
			this.craftingMatrix.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
		}
		}
	}
	
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		NBTTagList tagList = new NBTTagList();
		
		for (int i = 0; i < this.craftingMatrix.getSizeInventory(); i++) {
			ItemStack stack = this.craftingMatrix.getStackInSlot(i);
			if (stack != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte)i);
				stack.writeToNBT(stackTag);
				tagList.appendTag(stackTag);
			}
		}
		
		nbtTagCompound.setTag("Items", tagList);
	}*/
}
