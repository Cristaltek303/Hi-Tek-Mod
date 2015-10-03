package cristaltek.hitekmod.machines.crusher;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.recipes.CrusherRecipe;
import cristaltek.hitekmod.recipes.Recipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCrusher extends TileEntity implements IEnergyHandler, ISidedInventory
{
	private EnergyStorage energyStorage;
	private ItemStack[] inventory = new ItemStack[12];
	public int[] crushTime = new int[4];
	private int energyPerTick = 100;
	
	public boolean balance;
	
	public TileEntityCrusher()
	{
		energyStorage = new EnergyStorage(1000000);
	}

	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			for (int i = 0; i < 4; i++)
			{
				if (canCrush(i) && getEnergyStored(null) >= energyPerTick)
				{
					extractEnergy(null, energyPerTick, false);
					crushTime[i]++;
					if (crushTime[i] >= 200)
					{
						crushTime[i] = 0;
						crushItem(i);
					}
				}
				else
					crushTime[i] = 0;
			}
			if (balance)
				balanceInput();
		}
	}

	public void balanceInput()
	{
		for (int i = 0; i < 4; i++)
		{
			ItemStack itemStack1 = getStackInSlot(i);
			if (itemStack1 == null)
				continue;

			for (int j = 0; j < 4; j++)
			{
				if (i == j)
					continue;

				ItemStack itemStack2 = getStackInSlot(j);
				if (itemStack2 == null)
					setInventorySlotContents(j, itemStack1.splitStack(itemStack1.stackSize / 2));
				else if (itemStack1.getItem() == itemStack2.getItem())
				{
					int totalSize = itemStack1.stackSize + itemStack2.stackSize;
					itemStack1.stackSize = totalSize / 2 + totalSize % 2;
					itemStack2.stackSize = totalSize / 2;
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public double getCrushProgress(int slot)
	{
		return crushTime[slot] / 200D;
	}

	private boolean canCrush(int slot)
	{
		if (inventory[slot] == null)
		{
			return false;
		}
		else
		{
			CrusherRecipe recipe = Recipes.getCrusherRecipe(inventory[slot]);
			if (recipe == null)
				return false;
			if (inventory[2 * slot + 4] == null)
			{
				if (inventory[2 * slot + 5] == null)
					return true;
				else if (!inventory[2 * slot + 5].isItemEqual(recipe.secOutput))
					return false;
			}
			else if (!inventory[2 * slot + 4].isItemEqual(recipe.mainOutput))
				return false;

			int mainResult = inventory[2 * slot + 4].stackSize + recipe.mainOutput.stackSize;
			int secResult = inventory[2 * slot + 5].stackSize + recipe.secOutput.stackSize;
			return (mainResult <= getInventoryStackLimit() && mainResult <= inventory[2 * slot + 4].getMaxStackSize()
					&& secResult <= getInventoryStackLimit() && secResult <= inventory[2 * slot + 5].getMaxStackSize());
		}
	}

	public void crushItem(int slot)
	{
		if (canCrush(slot))
		{
			CrusherRecipe recipe = Recipes.getCrusherRecipe(inventory[slot]);

			if (inventory[2 * slot + 4] == null)
				inventory[2 * slot + 4] = recipe.mainOutput.copy();
			else if (inventory[2 * slot + 4].getItem() == recipe.mainOutput.getItem())
				inventory[2 * slot + 4].stackSize += recipe.mainOutput.stackSize;

			if (worldObj.rand.nextFloat() < recipe.chance)
			{
				if (inventory[2 * slot + 5] == null)
					inventory[2 * slot + 5] = recipe.secOutput.copy();
				else if (inventory[2 * slot + 5].getItem() == recipe.secOutput.getItem())
					inventory[2 * slot + 5].stackSize += recipe.secOutput.stackSize;
			}

			inventory[slot].stackSize--;

			if (inventory[slot].stackSize <= 0)
				inventory[slot] = null;
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return energyStorage.extractEnergy(maxExtract, simulate);
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return energyStorage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return energyStorage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return energyStorage.getMaxEnergyStored();
	}
	
	public void setEnergyStored(int energy)
	{
		this.energyStorage.setEnergyStored(energy);
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int size)
	{
		if (this.inventory[slot] != null)
		{
			ItemStack itemstack;

			if (this.inventory[slot].stackSize <= size)
			{
				itemstack = this.inventory[slot];
				this.inventory[slot] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.inventory[slot].splitStack(size);

				if (this.inventory[slot].stackSize == 0)
				{
					this.inventory[slot] = null;
				}

				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		if (inventory.length > slot)
			inventory[slot] = itemstack;
	}

	@Override
	public String getInventoryName()
	{
		return "Crusher";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		return (slot < 4) ? true : false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
		return null;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack itemstack, int side)
	{
		return isItemValidForSlot(slot, itemstack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack itemstack, int side)
	{
		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		energyStorage.writeToNBT(tag);
		tag.setBoolean("Balance", balance);

		for (int i = 0; i < inventory.length; i++)
		{
			if (i < 4)
				tag.setShort("CrushTime" + i, (short)crushTime[i]);
			if (inventory[i] != null)
			{
				NBTTagCompound inventorySlot = new NBTTagCompound();
				inventory[i].writeToNBT(inventorySlot);
				tag.setTag("Inventory" + i, inventorySlot);
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		energyStorage.readFromNBT(tag);
		balance = tag.getBoolean("Balance");

		for (int i = 0; i < inventory.length; i++)
		{
			if (i < 4)
				crushTime[i] = tag.getShort("CrushTime" + i);
			inventory[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Inventory" + i));
		}
	}
}
