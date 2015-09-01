package cristaltek.hitekmod.items;

import java.util.List;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cristaltek.hitekmod.Configs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class ItemMagnet extends ItemBase{
	
	private int distanceFromPlayer;
	
	public ItemMagnet(String name)
	{
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
	public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player) {
		if (!world.isRemote && player.isSneaking()) {
			item.setItemDamage(item.getItemDamage() == 0 ? 1 : 0);
		}
		return item;
	}
	
	@Override
	public void onUpdate(ItemStack item, World world, Entity entity, int i, boolean f) {
		if (world.isRemote || !isActivated(item) || !(entity instanceof EntityPlayer))
			return;
		
		EntityPlayer player = (EntityPlayer)entity;
		
		// Pick up items
		for (EntityItem itemToGet : this.getEntitiesInRange(EntityItem.class, world, (int)player.posX, (int)player.posY, (int)player.posZ)) {
			itemToGet.delayBeforeCanPickup = 50;
			
			EntityItemPickupEvent pickupEvent = new EntityItemPickupEvent(player, itemToGet);
			MinecraftForge.EVENT_BUS.post(pickupEvent);
			ItemStack itemStackToGet = itemToGet.getEntityItem();
			int stackSize = itemStackToGet.stackSize;
			
			if (pickupEvent.getResult() == Result.ALLOW || stackSize <= 0 || player.inventory.addItemStackToInventory(itemStackToGet)) {
				player.onItemPickup(itemToGet, stackSize);
				
				world.playSoundAtEntity(player, "random.pop", 0.15F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			}
		}
		
		// Pick up XP
		for (EntityXPOrb xpToGet : this.getEntitiesInRange(EntityXPOrb.class, world, (int)player.posX, (int)player.posY, (int)player.posZ)) {
			if (xpToGet.isDead || xpToGet.isInvisible())
				continue;
			
			int xpAmount = xpToGet.xpValue;
			xpToGet.xpValue = 0;
			player.xpCooldown = 0;
			player.addExperience(xpAmount);
			xpToGet.setDead();
			xpToGet.setInvisible(true);
			world.playSoundAtEntity(player, "random.orb", 0.08F, 0.5F * ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.8F));
		}
	}
	
	protected boolean isActivated(ItemStack item)
	{
		return item.getItemDamage() == 1;
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> getEntitiesInRange(Class<E> entityType, World world, int x, int y, int z) {
		return (List<E>)world.getEntitiesWithinAABB(entityType,
				AxisAlignedBB.getBoundingBox(x - this.distanceFromPlayer, y - this.distanceFromPlayer, z - this.distanceFromPlayer, x + this.distanceFromPlayer, y + this.distanceFromPlayer, z + this.distanceFromPlayer));
	}
}
