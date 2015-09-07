package cristaltek.hitekmod.items;

import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.Configs;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.EnumHelper;

public class ItemOPSword extends ItemSword implements IEnergyContainerItem
{
	//Sword Material
	public static final ToolMaterial material = EnumHelper.addToolMaterial("OPSwordMaterial", 0, 0, 0.0F, 96, 50);
	
	public ItemOPSword(String name)
	{
		super(material);
		setUnlocalizedName(name);
		setTextureName("ht:" + name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		GameRegistry.registerItem(this, name);
		
		//Sword characteristics
		setMaxStackSize(1);
		canRepair = false;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
		ItemStack charged = new ItemStack(item, 1, 0);
		charged.stackTagCompound = new NBTTagCompound();
		charged.stackTagCompound.setInteger("Energy", Configs.OPSword_maxEnergy);
		list.add(charged);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (getEnergyStored(stack) >= Configs.OPSword_energyPerHit)
			return false;
		else
			return true;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		if (getEnergyStored(stack) >= Configs.OPSword_energyPerHit)
			extractEnergy(stack, Configs.OPSword_energyPerHit, false);
		
		return true;
	}
	
	// Energy implementation (with durability bar & tooltip information)
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0 - (double)this.getEnergyStored(stack) / (double)this.getMaxEnergyStored(stack);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return this.getEnergyStored(stack) < this.getMaxEnergyStored(stack);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean debug)
	{
		info.add(this.getEnergyStored(stack) + " / " + this.getMaxEnergyStored(stack) + " RF");
	}
	
	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
	{
		if (container.stackTagCompound == null)
			container.stackTagCompound = new NBTTagCompound();
		
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyExtracted = Math.min(energy, maxExtract);
		
		if (!simulate) {
			energy -= energyExtracted;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyExtracted;
	}
	
	@Override
	public int getEnergyStored(ItemStack container)
	{
		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy"))
			return 0;
		
		return container.stackTagCompound.getInteger("Energy");
	}
	
	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return Configs.OPSword_maxEnergy;
	}
	
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
	{
		if (container.stackTagCompound == null)
			container.stackTagCompound = new NBTTagCompound();
		
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(Configs.OPSword_maxEnergy - energy, maxReceive);
		
		if (!simulate) {
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyReceived;
	}
}
