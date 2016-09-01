package cristaltek.hitekmod.items;

import java.util.List;
import java.util.Map;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyProvider;
import cristaltek.hitekmod.Configs;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLootBonus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemTDTool extends ItemPickaxe implements IEnergyContainerItem {
	// Tool material
	public static final ToolMaterial material = EnumHelper.addToolMaterial("TDToolMaterial", 100, 0, 10000.0F, 0.0F, 35);
	
	public ItemTDTool(String name) {
		super(material);
		setUnlocalizedName(name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		
		setRegistryName(name);
		GameRegistry.register(this);
		
		// Tool characteristics
		setMaxStackSize(1);
		canRepair = false;
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		super.getSubItems(itemIn, tab, subItems);
		ItemStack charged = new ItemStack(itemIn);
		charged.setTagCompound(new NBTTagCompound());
		charged.getTagCompound().setInteger("Energy", Configs.TDTool_maxEnergy);
		subItems.add(charged);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!stack.isItemEnchanted())
			stack.addEnchantment(Enchantment.getEnchantmentByID(35), 5);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote && playerIn.isSneaking()) {
			Map enchantments = EnchantmentHelper.getEnchantments(itemStackIn);
			boolean isFortune = enchantments.containsKey(Enchantment.getEnchantmentByID(35));
			enchantments.clear();
			EnchantmentHelper.setEnchantments(enchantments, itemStackIn);
			
			if (isFortune) {
				itemStackIn.addEnchantment(Enchantment.getEnchantmentByID(33), 1);
				playerIn.addChatMessage(new TextComponentString("Silk-Touch-Mode"));
			}
			else {
				itemStackIn.addEnchantment(Enchantment.getEnchantmentByID(35), 5);
				playerIn.addChatMessage(new TextComponentString("Fortune-Mode"));
			}
		}
		
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return false;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (getEnergyStored(stack) >= Configs.TDTool_energyPerUse)
			extractEnergy(stack, Configs.TDTool_energyPerUse, false);
		
		return true;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		if (getEnergyStored(stack) >= Configs.TDTool_energyPerUse)
			return efficiencyOnProperMaterial;
		
		return 0.0F;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1.0 - (double)getEnergyStored(stack) / (double)getMaxEnergyStored(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return getEnergyStored(stack) < getMaxEnergyStored(stack);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(getEnergyStored(stack) + " / " + getMaxEnergyStored(stack) + " RF");
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		if (!container.hasTagCompound())
			container.setTagCompound(new NBTTagCompound());
		
		int energy = container.getTagCompound().getInteger("Energy");
		int energyReceived = Math.min(Configs.TDTool_maxEnergy, maxReceive);
		
		if (!simulate) {
			energy += energyReceived;
			container.getTagCompound().setInteger("Energy", energy);
		}
		
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		if (!container.hasTagCompound())
			container.setTagCompound(new NBTTagCompound());
		
		int energy = container.getTagCompound().getInteger("Energy");
		int energyExtracted = Math.min(energy, maxExtract);
		
		if (!simulate) {
			energy -= energyExtracted;
			container.getTagCompound().setInteger("Energy", energy);
		}
		
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		if (container.hasTagCompound() && container.getTagCompound().hasKey("Energy"))
			return container.getTagCompound().getInteger("Energy");
		
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return Configs.TDTool_maxEnergy;
	}
}
