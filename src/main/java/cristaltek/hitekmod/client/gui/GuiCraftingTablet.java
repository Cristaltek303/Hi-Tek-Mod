package cristaltek.hitekmod.client.gui;

import org.lwjgl.opengl.GL11;

import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.client.gui.button.CraftingTabletButton;
import cristaltek.hitekmod.client.inventory.ContainerCraftingTablet;
import cristaltek.hitekmod.inventory.CraftingTabletContainer;
import cristaltek.hitekmod.network.message.CraftingTabletMessage;
import cristaltek.hitekmod.network.message.PacketHandler;
import cristaltek.hitekmod.reference.Reference;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiCraftingTablet extends GuiContainer {
	
    private CraftingTabletContainer container;
    private EntityPlayer player;

	private CraftingTabletButton balanceButton;
    private CraftingTabletButton spinButton;
    private CraftingTabletButton clearButton;
    
    public GuiCraftingTablet(CraftingTabletContainer container) {
        super(container);
        this.player = container.getPlayer();
        this.container = container;
        xSize = 220;
        ySize = 193;
    }
	
//	public GuiCraftingTablet(InventoryPlayer inventory, World world) {
//		super(new ContainerCraftingTablet(inventory, world));
//		xSize = 220;
//		ySize = 193;
//	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float ticks, int x, int y) {
		int guiX = (width - xSize) / 2;
		int guiY = (height - ySize) / 2;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.9F); //Alpha = Transparent
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/guiCraftingTablet.png"));
		drawTexturedModalRect(guiX, guiY, 0, 0, xSize, ySize);
	}
	
	@Override
    public void initGui() {
       super.initGui();

        int xStart = ((width - xSize) / 2) + 12;
        int yStart = (height - ySize) / 2;

        this.buttonList.add(this.balanceButton = new CraftingTabletButton(1, xStart + 10, yStart + 16, 11, 16, CraftingTabletButton.BALANCE));
        this.buttonList.add(this.spinButton = new CraftingTabletButton(2, xStart + 10, yStart + 32, 11, 11, CraftingTabletButton.SPIN));
        this.buttonList.add(this.clearButton = new CraftingTabletButton(3, xStart + 10, yStart + 48, 11, 11, CraftingTabletButton.EMPTY));
    }
	
	@Override
    protected void actionPerformed(GuiButton button) {
        if (button == balanceButton) {
            this.container.balanceMatrix();
            PacketHandler.INSTANCE.sendToServer(new CraftingTabletMessage(CraftingTabletMessage.BALANCE_MATRIX));
        } else if (button == spinButton) {
            this.container.spinMatrix();
            PacketHandler.INSTANCE.sendToServer(new CraftingTabletMessage(CraftingTabletMessage.SPIN_MATRIX));
        } else if (button == clearButton) {
            this.container.clearMatrix();
            PacketHandler.INSTANCE.sendToServer(new CraftingTabletMessage(CraftingTabletMessage.CLEAR_MATRIX));
        }
    }
	
	
}
