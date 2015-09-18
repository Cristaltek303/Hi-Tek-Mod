package cristaltek.hitekmod.machines.smelter;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.inventory.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerSmelter extends ContainerBase
{
	private TileEntitySmelter smelter;

	public ContainerSmelter(EntityPlayer player, TileEntitySmelter smelter)
	{
		this.smelter = smelter;
		this.addPlayerInventorySlots(player.inventory);
		
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
		this.addSlotToContainer(new Slot(smelter, 9, 18, 62));
		this.addSlotToContainer(new Slot(smelter, 10, 39, 62));
		this.addSlotToContainer(new Slot(smelter, 11, 60, 62));
		this.addSlotToContainer(new Slot(smelter, 12, 81, 62));
		this.addSlotToContainer(new Slot(smelter, 13, 102, 62));
		this.addSlotToContainer(new Slot(smelter, 14, 123, 62));
		this.addSlotToContainer(new Slot(smelter, 15, 144, 62));
		this.addSlotToContainer(new Slot(smelter, 16, 165, 62));
		this.addSlotToContainer(new Slot(smelter, 17, 186, 62));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (ICrafting craft : (List<ICrafting>)this.crafters)
			craft.sendProgressBarUpdate(this, 0, this.smelter.getEnergyStored(null));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int value)
	{
		if (id == 0)
			this.smelter.setEnergyStored(value);
	}
}
