package florasoma.common;

import florasoma.FloraSoma;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class Config
{
    protected static File configFolder;
    protected static File configFile;
    public static Configuration config;

    public static boolean genCorruption;
    public static boolean corruptionSpread;
    public static int corrupterRarity;
    public static int corrupterVeinSize;

    public static void initConfig(FMLPreInitializationEvent event)
    {
        configFolder = event.getModConfigurationDirectory();
        configFile = new File(configFolder.getAbsolutePath() + "/FloraSoma.cfg");
        config = new Configuration(configFile);
        updateConfig();
    }

    public static void updateConfig()
    {
        genCorruption = config.get(config.CATEGORY_GENERAL, "Generate Nether Corrupter", true).getBoolean(true);
        corruptionSpread = config.get(config.CATEGORY_GENERAL, "Corruption Spread", true).getBoolean(true);
        corrupterRarity = config.get(config.CATEGORY_GENERAL, "Corruption Rarity", 8).getInt(8);
        corrupterVeinSize = config.get(config.CATEGORY_GENERAL, "Corruption Vein Size", 16).getInt(16);

        if (config.hasChanged())
            config.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (FloraSoma.MOD_ID.equals(event.modID))
            updateConfig();
    }

}
