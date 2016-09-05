package cristaltek.hitekmod.items;

import cristaltek.hitekmod.Configs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMagnet extends ItemBase {
	
	protected int distanceFromPlayer;

	public ItemMagnet(String name) {
		super(name);
		setMaxStackSize(1);
		this.distanceFromPlayer = Configs.magnet_strength;
		canRepair = false;
		setMaxDamage(0);
	}

	@Override
	public boolean hasEffect(ItemStack item) {
		return isActivated(item);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (!worldIn.isRemote && playerIn.isSneaking()) {
			itemStackIn.setItemDamage(itemStackIn.getItemDamage() == 0 ? 1 : 0);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn); // <- keine ahnung ob das stimmt...
	}
	
	@SuppressWarnings("rawtypes")
	@Override
		public void onUpdate(ItemStack item, World world, Entity entity, int i, boolean f) {
		if (world.isRemote)
	return;
		if (!isActivated(item))
	return;
		if (!(entity instanceof EntityPlayer))
	return;
	}

	
	
	protected boolean isActivated(ItemStack item) {
		return item.getItemDamage() == 1;
		}
	
}


