package cristaltek.hitekmod.machines.crusher;

import java.util.ArrayList;
import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.inventory.ContainerBase;
import cristaltek.hitekmod.recipes.Recipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class ContainerCrusher extends ContainerBase
{
	private TileEntityCrusher crusher;

	public ContainerCrusher(EntityPlayer player, TileEntityCrusher crusher)
	{
		this.crusher = crusher;
		this.addPlayerInventorySlots(player.inventory);
		
		//Input
		for (int i = 0; i < 4; i++)
			addSlotToContainer(new Slot(crusher, i, 35 + i * 44, 20));
		//Output
		for (int i = 0; i < 8; i++)
			addSlotToContainer(new SlotFurnace(player, crusher, i + 4, 24 + i * 22, 62));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (ICrafting craft : (List<ICrafting>)this.crafters)
		{
			craft.sendProgressBarUpdate(this, 0, this.crusher.getEnergyStored(null));
			for (int i = 0; i < 4; i++)
				craft.sendProgressBarUpdate(this, i + 1, crusher.crushTime[i]);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value)
	{
		if (id == 0)
			this.crusher.setEnergyStored(value);
		else
			crusher.crushTime[id - 1] = value;
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
			
			if (slotId >= 0 && slotId < 36 && Recipes.getCrusherRecipe(itemstack) != null)
			{
				if (!this.mergeItemStack(itemstack1, 36, 40, false))
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
