package cristaltek.hitekmod.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cristaltek.hitekmod.inventory.ContainerCraftingTablet;
import cristaltek.hitekmod.machines.smelter.TileEntitySmelter;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class BalanceMessage implements IMessage
{
	int x, y, z;
	boolean balance;
	
	public BalanceMessage()
	{
	}
	
	public BalanceMessage(int x, int y, int z, boolean balance)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.balance = balance;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		balance = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeBoolean(balance);
	}

	public static class Handler implements IMessageHandler<BalanceMessage, IMessage>
	{
		@Override
		public IMessage onMessage(BalanceMessage message, MessageContext ctx)
		{
			TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
			if (tileEntity instanceof TileEntitySmelter)
			{
				TileEntitySmelter smelter = (TileEntitySmelter)tileEntity;
				smelter.balance = message.balance;
			}
			return null;
		}
	}
}
