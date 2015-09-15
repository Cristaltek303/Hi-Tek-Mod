package cristaltek.hitekmod.machines.smelter;

import cristaltek.hitekmod.inventory.ContainerBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerSmelter extends ContainerBase
{
	private TileEntitySmelter smelter;

	public ContainerSmelter(EntityPlayer player, TileEntitySmelter smelter)
	{
		this.smelter = smelter;
		this.addPlayerInventorySlots(player.inventory);
		
		for (int i = 0; i < 4; ++i)
		{
			final int armorType = i;
			final Entity entity = player;
			this.addSlotToContainer(new Slot(player.inventory, player.inventory.getSizeInventory() - 1 - i, 40 + i * 20, 60));
		}
		
		//Input
		this.addSlotToContainer(new Slot(smelter, 0, 18, 20));
		this.addSlotToContainer(new Slot(smelter, 1, 39, 20));
		this.addSlotToContainer(new Slot(smelter, 2, 60, 20));
		this.addSlotToContainer(new Slot(smelter, 3, 81, 20));
		this.addSlotToContainer(new Slot(smelter, 4, 102, 20));
		this.addSlotToContainer(new Slot(smelter, 5, 123, 20));
		this.addSlotToContainer(new Slot(smelter, 6, 144, 20));
		this.addSlotToContainer(new Slot(smelter, 7, 165, 20));
		this.addSlotToContainer(new Slot(smelter, 8, 186, 20));
		//output
		this.addSlotToContainer(new Slot(smelter, 0, 18, 62));
		this.addSlotToContainer(new Slot(smelter, 1, 39, 62));
		this.addSlotToContainer(new Slot(smelter, 2, 60, 62));
		this.addSlotToContainer(new Slot(smelter, 3, 81, 62));
		this.addSlotToContainer(new Slot(smelter, 4, 102, 62));
		this.addSlotToContainer(new Slot(smelter, 5, 123, 62));
		this.addSlotToContainer(new Slot(smelter, 6, 144, 62));
		this.addSlotToContainer(new Slot(smelter, 7, 165, 62));
		this.addSlotToContainer(new Slot(smelter, 8, 186, 62));
	}

}
