package cristaltek.hitekmod.items;

import cristaltek.hitekmod.HiTekMod;
import cristaltek.hitekmod.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBase extends Item {
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setCreativeTab(HiTekMod.tabHiTekMod);
		
		setRegistryName(name);
		GameRegistry.register(this);
	}
}
