package cristaltek.hitekmod.machines.smelter;

import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.inventory.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerSmelter extends ContainerBase
{
	private TileEntitySmelter smelter;

	public ContainerSmelter(EntityPlayer player, TileEntitySmelter smelter)
	{
		this.smelter = smelter;
		this.addPlayerInventorySlots(player.inventory);
		
		//Input
		for (int i = 0; i < 9; i++)
			addSlotToContainer(new Slot(smelter, i, 18 + i * 21, 20));
		//Output
		for (int i = 0; i < 9; i++)
			addSlotToContainer(new SlotFurnace(player, smelter, i + 9, 18 + i * 21, 62));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (ICrafting craft : (List<ICrafting>)this.crafters)
		{
			craft.sendProgressBarUpdate(this, 0, this.smelter.getEnergyStored(null));
			for (int i = 0; i < 9; i++)
				craft.sendProgressBarUpdate(this, i + 1, smelter.smeltTime[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value)
	{
		if (id == 0)
			this.smelter.setEnergyStored(value);
		else
			smelter.smeltTime[id - 1] = value;
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
			
			if (slotId >= 0 && slotId < 36 && FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null)
			{
				if (!this.mergeItemStack(itemstack1, 36, 45, false))
					return null;
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
