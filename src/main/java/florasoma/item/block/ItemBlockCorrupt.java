package florasoma.item.block;

import florasoma.util.EnumType;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCorrupt extends ItemBlock
{
    Block block;

    public ItemBlockCorrupt(Block block)
    {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    public String getUnlocalizedName(ItemStack itemstack)
    {
        return super.getUnlocalizedName() + "." + EnumType.Corrupt.fromMetadata(itemstack.getItemDamage()).getName();
    }

}
