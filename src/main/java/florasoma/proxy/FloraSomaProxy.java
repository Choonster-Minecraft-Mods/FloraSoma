package florasoma.proxy;

import florasoma.FloraSoma;
import florasoma.Recipes;
import florasoma.block.BlocksFS;
import florasoma.world.gen.Generators;
import net.minecraft.init.Items;

public class FloraSomaProxy implements IProxy
{

    public void preInit()
    {
        BlocksFS.register();
        Recipes.register();
        FloraSoma.tab.init(Items.brick);
    }

    public void init()
    {
        Generators.register();
    }

    public void postInit()
    {

    }
}
