package cristaltek.hitekmod.client.inventory;

import java.util.UUID;

import cristaltek.hitekmod.NBTHelper;
import cristaltek.hitekmod.items.ItemCraftingTablet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryCraftingTablet extends InventoryCrafting {

	public ItemStack parent;
	public EntityPlayer player;
	
	protected ItemStack[] inventory;
	
	private Container eventHandler;
	
	public InventoryCraftingTablet(EntityPlayer player, ItemStack itemStack) {
		super(null, 3, 3);
		
		this.parent = itemStack;
		this.player = player;
		
		this.inventory = new ItemStack[this.getSizeInventory()];
		
		readFromNBT(parent.getTagCompound());
	}

	public void setEventHandler(Container eventHandler) {
		this.eventHandler = eventHandler;
	}
	
	@Override
	public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
		inventory[slotIndex] = itemStack;
		if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
			itemStack.stackSize = getInventoryStackLimit();
		}
		eventHandler.onCraftMatrixChanged(this);
	}
	
	@Override
	public ItemStack getStackInRowAndColumn(int row, int col) {
		if (row >= 0 && row < 3) {
			int k = row + col * 3;
			return this.getStackInSlot(k);
		}
		else {
			return null;
		}
	}
	
	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		return slotIndex >= this.getSizeInventory() ? null : this.inventory[slotIndex];
	}
	
	@Override
	public int getSizeInventory() {
		return 9;
	}
	
	@Override
	public ItemStack decrStackSize(int slotIndex, int amount) {
        if (this.inventory[slotIndex] != null) {
            ItemStack itemstack;

            if (this.inventory[slotIndex].stackSize <= amount) {
                itemstack = this.inventory[slotIndex];
                this.inventory[slotIndex] = null;
                this.eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            } else {
                itemstack = this.inventory[slotIndex].splitStack(amount);

                if (this.inventory[slotIndex].stackSize == 0) {
                    this.inventory[slotIndex] = null;
                }

                this.eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
        } else {
            return null;
        }
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		return null;
	}
	
	@Override
	public String getInventoryName() {
		return "crafting.inventory";
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
	public void openInventory() {
	}
	
	@Override
	public void closeInventory() {
	}

	public void onGuiSaved(EntityPlayer player) {
		if (parent != null) {
	        NBTTagCompound nbtTagCompound = parent.getTagCompound();

	        if (nbtTagCompound == null) {
	            nbtTagCompound = new NBTTagCompound();
	        }

	        writeToNBT(nbtTagCompound);
	        parent.setTagCompound(nbtTagCompound);
		}
	}
	
	public ItemStack findParentItemStack(EntityPlayer entityPlayer) {
		if (NBTHelper.hasUUID(parent)) {
            UUID parentUUID = new UUID(parent.getTagCompound().getLong("MostSigUUID"), parent.getTagCompound().getLong("LeastSigUUID"));
            for (int i = 0; i < entityPlayer.inventory.getSizeInventory(); i++) {
                ItemStack itemStack = entityPlayer.inventory.getStackInSlot(i);

                if (itemStack != null && itemStack.getItem() instanceof ItemCraftingTablet && NBTHelper.hasUUID(itemStack)) {
                    if (itemStack.getTagCompound().getLong("MostSigUUID") == parentUUID.getMostSignificantBits() &&
                            itemStack.getTagCompound().getLong("LeastSigUUID") == parentUUID.getLeastSignificantBits()) {
                        return itemStack;
                    }
                }
            }
		}
		
		return null;
	}
	
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		parent = findParentItemStack(player);
		
		if (parent != null) {
			nbtTagCompound = parent.getTagCompound();
			
			if (nbtTagCompound != null && nbtTagCompound.hasKey("Items")) {
				NBTTagList tagList = nbtTagCompound.getTagList("Items", 10);
				this.inventory = new ItemStack[this.getSizeInventory()];
				
				for (int i = 0; i < tagList.tagCount(); i++) {
					NBTTagCompound stackTag = tagList.getCompoundTagAt(i);
					int slot = stackTag.getByte("Slot");
					if (i >= 0 && i <= inventory.length) {
						this.inventory[slot] = ItemStack.loadItemStackFromNBT(stackTag);
					}
				}
			}
		}
	}

	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		nbtTagCompound = findParentItemStack(player).getTagCompound();
		
		NBTTagList tagList = new NBTTagList();
		
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte)i);
				inventory[i].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		
		nbtTagCompound.setTag("Items", tagList);
	}
}
