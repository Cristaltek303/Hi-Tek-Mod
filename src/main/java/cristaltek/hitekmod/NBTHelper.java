package cristaltek.hitekmod;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public class NBTHelper {

    public static boolean hasUUID(ItemStack itemStack) {
        return has_tag(itemStack, "MostSigUUID") && has_tag(itemStack, "LeastSigUUID");
    }

    public static void setUUID(ItemStack itemStack) {
        initNBTCompound(itemStack);

        if (!hasUUID(itemStack)) {
            UUID itemUUID = UUID.randomUUID();

            setLong(itemStack, "MostSigUUID", itemUUID.getMostSignificantBits());
            setLong(itemStack, "LeastSigUUID", itemUUID.getLeastSignificantBits());
        }
    }

    public static UUID getUUID(ItemStack itemStack) {
        initNBTCompound(itemStack);

        if (hasUUID(itemStack)) {
            return new UUID(itemStack.getTagCompound().getLong("MostSigUUID"),
                    itemStack.getTagCompound().getLong("LeastSigUUID"));
        }
        return null;
    }

    public static void initNBTCompound(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
    }

    public static boolean has_tag(ItemStack itemStack, String tag) {
        return itemStack != null && itemStack.hasTagCompound() && itemStack.stackTagCompound.hasKey(tag);
    }

    public static void setLong(ItemStack itemStack, String tag, Long value) {
        initNBTCompound(itemStack);
        itemStack.stackTagCompound.setLong(tag, value);
    }
}
