package florasoma.proxy;

import florasoma.FloraSoma;
import florasoma.common.ConfigHandler;
import florasoma.util.BiomeBlockReplacer;
import florasoma.util.BiomeRegistrant;
import florasoma.util.TextureManager;
import net.minecraftforge.common.MinecraftForge;

import java.util.regex.Pattern;

public class ClientProxy extends CommonProxy
{
    public void preInit()
    {
        super.preInit();
        TextureManager.registerVariants();
    }

    public void init()
    {
        super.init();
        TextureManager.registerTextures();
    }

    public void postInit()
    {
        BiomeRegistrant.init();

        for (String name : ConfigHandler.getAddBiomes()) {
            if (Pattern.matches("\\d+", name))
                BiomeRegistrant.addBiomeById(Integer.parseInt(name));
            else
                BiomeRegistrant.addBiomeByName(name);
        }
        for (String name : ConfigHandler.getAddTypes()) {
            FloraSoma.instance.log.info(String.format("Adding all %s biomes as village biomes.", name));
            BiomeRegistrant.addBiomesByTypeName(name);
        }

        for (String name : ConfigHandler.getRemoveBiomes()) {
            if (Pattern.matches("\\d+", name))
                BiomeRegistrant.removeBiomeById(Integer.parseInt(name));
            else
                BiomeRegistrant.removeBiomeByName(name);
        }
        for (String name : ConfigHandler.getRemoveTypes()) {
            FloraSoma.instance.log.info(String.format("Removing all %s biomes from village biomes.", name));
            BiomeRegistrant.removeBiomesByTypeName(name);
        }

        MinecraftForge.TERRAIN_GEN_BUS.register(new BiomeBlockReplacer());

    }
}
