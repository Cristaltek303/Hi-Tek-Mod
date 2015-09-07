package cristaltek.hitekmod.items;

import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cristaltek.hitekmod.Configs;
import cristaltek.hitekmod.HiTekMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemhtArmor extends ItemArmor implements IEnergyContainerItem
{
	//Armor Material
	public static final ArmorMaterial material = EnumHelper.addArmorMaterial("htArmorMaterial", -1, new int[]{3,8,6,3}, 35);
	
	public ItemhtArmor(String name, int type)
	{
		super(material, 0, type);
		setUnlocalizedName(name);
		setTextureName("ht:" + name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		GameRegistry.registerItem(this, name);
		
		//Armor characteristics
		setMaxStackSize(1);
		setMaxDamage(0);
		canRepair = false;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return "ht:textures/models/armor/XLF-1 Armor_layer_" + (this.armorType == 2 ? "2" : "1") + ".png";
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemstack)
	{
		ItemStack helmet = player.getCurrentArmor(3);
		ItemStack chestplate = player.getCurrentArmor(2);
		ItemStack leggings = player.getCurrentArmor(1);
		ItemStack boots = player.getCurrentArmor(0);
		
		if (helmet != null && helmet.getItem() instanceof ItemhtArmor &&
			chestplate != null && chestplate.getItem() instanceof ItemhtArmor &&
			leggings != null && leggings.getItem() instanceof ItemhtArmor &&
			boots != null && boots.getItem() instanceof ItemhtArmor)
		{
			// Full HiTek Armor
			if (getEnergyStored(helmet) >= Configs.htArmor_energyPerTick &&
				getEnergyStored(chestplate) >= Configs.htArmor_energyPerTick &&
				getEnergyStored(leggings) >= Configs.htArmor_energyPerTick &&
				getEnergyStored(boots) >= Configs.htArmor_energyPerTick)
			{
				if (player.capabilities.isFlying)
				{
					extractEnergy(helmet, Configs.htArmor_energyPerTick, false);
					extractEnergy(chestplate, Configs.htArmor_energyPerTick, false);
					extractEnergy(leggings, Configs.htArmor_energyPerTick, false);
					extractEnergy(boots, Configs.htArmor_energyPerTick, false);
				}
				
				player.capabilities.allowFlying = true;
				player.capabilities.setFlySpeed(0.1F);
			}
			else
			{
				player.capabilities.allowFlying = false;
				player.capabilities.isFlying = false;
				player.capabilities.setFlySpeed(0.05F);
			}
		}
	}
	
	// Energy implementation (with durability bar & tooltip information)
	
	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return 1.0 - (double)this.getEnergyStored(stack) / (double)this.getMaxEnergyStored(stack);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
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
		return Configs.htArmor_maxEnergy;
	}
	
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
	{
		if (container.stackTagCompound == null)
			container.stackTagCompound = new NBTTagCompound();
		
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(Configs.htArmor_maxEnergy - energy, maxReceive);
		
		if (!simulate)
		{
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyReceived;
	}
}
