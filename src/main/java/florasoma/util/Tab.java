package florasoma.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Tab extends CreativeTabs
{
    Item display;

    public Tab(String name)
    {
        super(name);
    }

    public Tab init (Item item)
    {
        display = item;
        return this;
    }

    @Override
    public Item getTabIconItem()
    {
        return display;
    }
}
