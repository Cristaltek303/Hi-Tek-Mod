package cristaltek.hitekmod.items.rendering;

import org.lwjgl.opengl.GL11;

import cristaltek.hitekmod.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderer implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON);
	}
	
	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {
		EntityLivingBase entityLivingBase = (EntityLivingBase)data[1];
		
		GL11.glPushMatrix();
		TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
		Item item = itemStack.getItem();
		
		IIcon iicon = entityLivingBase.getItemIcon(itemStack, 0);
		if (iicon == null) {
			GL11.glPopMatrix();
			return;
		}
		
		texturemanager.bindTexture(texturemanager.getResourceLocation(itemStack.getItemSpriteNumber()));
		TextureUtil.func_152777_a(false, false, 1.0F);
		
		GL11.glScalef(2.0F, 2.0F, 2.0F);
		
		if (item == ModItems.OPSword)
			GL11.glTranslatef(-0.55F, 0.05F, 0.0F);
		else if (item == ModItems.TDTool)
			GL11.glTranslatef(-0.35F, -0.15F, 0.0F);
		
		Tessellator tessellator = Tessellator.instance;
		float f = iicon.getMinU();
		float f1 = iicon.getMaxU();
		float f2 = iicon.getMinV();
		float f3 = iicon.getMaxV();
		net.minecraft.client.renderer.ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0325F);
		
		texturemanager.bindTexture(texturemanager.getResourceLocation(itemStack.getItemSpriteNumber()));
		TextureUtil.func_147945_b();
		
		GL11.glPopMatrix();
	}
}
