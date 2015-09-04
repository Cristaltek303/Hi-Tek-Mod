package cristaltek.hitekmod.machines.energycube;

import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.inventory.ContainerBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ContainerEnergyCube extends ContainerBase
{
	private TileEntityEnergyCube energyCube;
	
	public ContainerEnergyCube(EntityPlayer player, TileEntityEnergyCube energyCube)
	{
		this.energyCube = energyCube;
		this.addPlayerInventorySlots(player.inventory);
		
		for (int i = 0; i < 4; ++i)
		{
			final int armorType = i;
			final Entity entity = player;
			this.addSlotToContainer(new Slot(player.inventory, player.inventory.getSizeInventory() - 1 - i, 40 + i * 20, 60)
			{
				@Override
				public boolean isItemValid(ItemStack itemstack)
				{
					return itemstack.getItem().isValidArmor(itemstack, armorType, entity);
				}
				
				@SideOnly(Side.CLIENT)
				@Override
				public IIcon getBackgroundIconIndex()
				{
					return ItemArmor.func_94602_b(armorType);
				}
			});
		}
		
		this.addSlotToContainer(new Slot(energyCube, 0, 40, 41)
		{
			@Override
			public boolean isItemValid(ItemStack itemstack)
			{
				return itemstack.getItem() instanceof IEnergyContainerItem;
			}
		});
		this.addSlotToContainer(new Slot(energyCube, 1, 162, 41)
		{
			@Override
			public boolean isItemValid(ItemStack itemstack)
			{
				return itemstack.getItem() instanceof IEnergyContainerItem;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (ICrafting craft : (List<ICrafting>)this.crafters)
			craft.sendProgressBarUpdate(this, 0, this.energyCube.getEnergyStored(null));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value)
	{
		if (id == 0)
			this.energyCube.setEnergyStored(value);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotId);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (slotId >= 0 && slotId < 40 && itemstack.getItem() instanceof IEnergyContainerItem)
			{
				if (!this.mergeItemStack(itemstack1, 40, 42, true))
					return null;
			}
			else if (slotId >= 40)
			{
				if (itemstack.getItem() instanceof ItemArmor && !((Slot)this.inventorySlots.get(36 + ((ItemArmor)itemstack.getItem()).armorType)).getHasStack())
				{
					int j = 36 + ((ItemArmor)itemstack.getItem()).armorType;
					
					if (!this.mergeItemStack(itemstack1, j, j + 1, false))
						return null;
				}
				else
				{
					if (!this.mergeItemStack(itemstack1, 0, 36, true))
						return null;
				}
			}
			else
				return super.transferStackInSlot(player, slotId);
			
			if (itemstack1.stackSize == 0)
				slot.putStack(null);
			else
				slot.onSlotChanged();
		}
		return itemstack;
	}
}
