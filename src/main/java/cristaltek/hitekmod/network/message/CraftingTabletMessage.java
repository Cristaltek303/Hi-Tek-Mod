package cristaltek.hitekmod.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.inventory.CraftingTabletContainer;
import cristaltek.hitekmod.reference.GUIs;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class CraftingTabletMessage implements IMessage {
	
	int action;
	
	public static final int CLEAR_MATRIX = 1;
    public static final int SPIN_MATRIX = 2;
    public static final int BALANCE_MATRIX = 3;
    public static final int SPIN_MATRIX_LEFT = 4;
    public static final int OPEN_CRAFTINGTABLET = 5;
    
    public CraftingTabletMessage (){ }
    
    public CraftingTabletMessage (int action){ 
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
        /**
         * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
         * is needed.
         *
         * @param message The message
         * @param ctx
         * @return an optional return message
         */
        @Override
        public IMessage onMessage(CraftingTabletMessage message, MessageContext ctx) {
        	CraftingTabletContainer container;
            switch (message.action) {
                case CraftingTabletMessage.BALANCE_MATRIX:
                    container = (CraftingTabletContainer)ctx.getServerHandler().playerEntity.openContainer;
                    container.balanceMatrix();
                    break;
                case CraftingTabletMessage.SPIN_MATRIX:
                    container = (CraftingTabletContainer)ctx.getServerHandler().playerEntity.openContainer;
                    container.spinMatrix();
                    break;
                case CraftingTabletMessage.SPIN_MATRIX_LEFT:
                    container = (CraftingTabletContainer)ctx.getServerHandler().playerEntity.openContainer;
                    container.spinMatrix(true);
                    break;
                case CraftingTabletMessage.CLEAR_MATRIX:
                    container = (CraftingTabletContainer)ctx.getServerHandler().playerEntity.openContainer;
                    container.clearMatrix();
                    break;
                case CraftingTabletMessage.OPEN_CRAFTINGTABLET:
                    EntityPlayer player = ctx.getServerHandler().playerEntity;
                    player.openGui(HiTekMod.instance, GUIs.CRAFTINGTABLET.ordinal(), player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
                    break;
            }

            return null;
        }
    }

}
