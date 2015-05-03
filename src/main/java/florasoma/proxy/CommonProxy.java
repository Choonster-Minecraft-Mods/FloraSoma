package florasoma.proxy;

import florasoma.FloraSoma;
import florasoma.Recipes;
import florasoma.block.BlocksFS;
import florasoma.common.ConfigMain;
import florasoma.world.gen.Generators;
import florasoma.world.gen.VillageGenReplacer;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonProxy implements IProxy
{

    public void preInit()
    {
        BlocksFS.register();
        Recipes.register();
        FloraSoma.tab.init(Items.brick);


        if (!ConfigMain.enabledCustomVillageGeneration.getBoolean(false)) return;
        FloraSoma.instance.log.info("Registering replacer for village generation.");
        MinecraftForge.TERRAIN_GEN_BUS.register(new VillageGenReplacer());
    }

    public void init()
    {
        FMLCommonHandler.instance().bus().register(new ConfigMain());

        Generators.register();
    }

    public void postInit()
    {

    }

}
