package cristaltek.hitekmod.items;

import cristaltek.hitekmod.HiTekMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemTDTool extends ItemPickaxe {

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
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		return efficiencyOnProperMaterial;
	}
}
