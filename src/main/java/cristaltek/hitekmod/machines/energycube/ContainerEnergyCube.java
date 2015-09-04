package cristaltek.hitekmod.machines.energycube;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.inventory.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public class ContainerEnergyCube extends ContainerBase
{
	private TileEntityEnergyCube energyCube;
	
	public ContainerEnergyCube(EntityPlayer player, TileEntityEnergyCube energyCube)
	{
		this.energyCube = energyCube;
		this.addPlayerInventorySlots(player.inventory);
		this.addSlotToContainer(new Slot(energyCube, 1, 162, 41));
		this.addSlotToContainer(new Slot(energyCube, 0, 40, 41));
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
}
