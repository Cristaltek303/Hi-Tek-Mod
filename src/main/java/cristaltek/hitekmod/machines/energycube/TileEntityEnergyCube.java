package cristaltek.hitekmod.machines.energycube;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import cristaltek.hitekmod.Configs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityEnergyCube extends TileEntity implements IEnergyHandler, ISidedInventory
{

	private EnergyStorage energyStorage;
	private ItemStack[] inventory = new ItemStack[2];
	private int receiveEnergySlot = 1000000;
	private int extractEnergySlot = 1000000;
	
	public TileEntityEnergyCube()
	{
		energyStorage = new EnergyStorage(Configs.energyCube_maxEnergy);
	}
	
	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			// Energy input
			if (inventory[0] != null && inventory[0].getItem() instanceof IEnergyContainerItem)
			{
				IEnergyContainerItem input = (IEnergyContainerItem)inventory[0].getItem();
				int energyToStore = input.extractEnergy(inventory[0], receiveEnergySlot, true);
				int energyStored = this.energyStorage.receiveEnergy(energyToStore, false);
				input.extractEnergy(inventory[0], energyStored, false);
			}
			
			// Energy output
			if (inventory[1] != null && inventory[1].getItem() instanceof IEnergyContainerItem)
			{
				IEnergyContainerItem output = (IEnergyContainerItem)inventory[1].getItem();
				int energyToExtract = this.energyStorage.extractEnergy(extractEnergySlot, true);
				int energyExtracted = output.receiveEnergy(inventory[1], energyToExtract, false);
				this.energyStorage.extractEnergy(energyExtracted, false);
			}
			
			// Block energy
			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			{
				int targetX = this.xCoord + dir.offsetX;
				int targetY = this.yCoord + dir.offsetY;
				int targetZ = this.zCoord + dir.offsetZ;
				
				TileEntity tileEntity = worldObj.getTileEntity(targetX, targetY, targetZ);
				if (tileEntity instanceof IEnergyReceiver && !(tileEntity instanceof TileEntityEnergyCube))
				{
					IEnergyReceiver energyReceiver = (IEnergyReceiver)tileEntity;
					if (energyReceiver.canConnectEnergy(dir.getOpposite()))
					{
						int energyToSend = this.energyStorage.extractEnergy(1000, true);
						int energySent = energyReceiver.receiveEnergy(dir.getOpposite(), energyToSend, false);
						this.energyStorage.extractEnergy(energySent, false);
					}
				}
			}
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
	public ItemStack getStackInSlotOnClosing(int slot)
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
		return "EnergyCube";
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
		if (itemstack.getItem() instanceof IEnergyContainerItem)
			return true;
		else
			return false;
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
		this.energyStorage.writeToNBT(tag);

		NBTTagCompound inputSlot = new NBTTagCompound();
		if (this.inventory[0] != null)
			this.inventory[0].writeToNBT(inputSlot);
		tag.setTag("inputSlot", inputSlot);

		NBTTagCompound outputSlot = new NBTTagCompound();
		if (this.inventory[1] != null)
			this.inventory[1].writeToNBT(outputSlot);
		tag.setTag("outputSlot", outputSlot);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.energyStorage.readFromNBT(tag);
		
		this.inventory[0] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("inputSlot"));
		this.inventory[1] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("outputSlot"));
	}
}
