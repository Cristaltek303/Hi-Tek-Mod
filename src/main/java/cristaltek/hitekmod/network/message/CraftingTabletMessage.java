package cristaltek.hitekmod.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cristaltek.hitekmod.client.inventory.ContainerCraftingTablet;
import io.netty.buffer.ByteBuf;

public class CraftingTabletMessage implements IMessage {

	int action;
	
	public static final int CLEAR_MATRIX = 1;
	public static final int SPIN_MATRIX = 2;
	public static final int BALANCE_MATRIX = 3;
//	public static final int OPEN_IBENCH = 4;
	
	public CraftingTabletMessage() {
	}
	
	public CraftingTabletMessage(int action) {
		this.action = action;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		action = ByteBufUtils.readVarShort(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeVarShort(buf, action);
	}

	public static class Handler implements IMessageHandler<CraftingTabletMessage, IMessage> {

		@Override
		public IMessage onMessage(CraftingTabletMessage message, MessageContext ctx) {
			ContainerCraftingTablet container;
			switch (message.action) {
				case CraftingTabletMessage.BALANCE_MATRIX:
					container = (ContainerCraftingTablet)ctx.getServerHandler().playerEntity.openContainer;
					container.balanceMatrix();
					break;
				case CraftingTabletMessage.SPIN_MATRIX:
					container = (ContainerCraftingTablet)ctx.getServerHandler().playerEntity.openContainer;
					container.spinMatrix();
					break;
				case CraftingTabletMessage.CLEAR_MATRIX:
					container = (ContainerCraftingTablet)ctx.getServerHandler().playerEntity.openContainer;
					container.clearMatrix();
					break;
			}
			
			return null;
		}
	}
}
