package florasoma.common;

import florasoma.FloraSoma;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.*;
import java.util.*;

public class ConfigMain
{
    protected static File configFolder;
    protected static File configFileMain;
    public static Configuration configMain;
    static final String CATG = Configuration.CATEGORY_GENERAL;

    public static Property enabledCustomVillageGeneration;
    public static Property villageSpawnDensity;
    public static Property minimumVillageDistance;
    public static Property villageSize;

    public static boolean genCorruption;
    public static boolean corruptionSpread;
    public static int corrupterRarity;
    public static int corrupterVeinSize;

    public static void initConfig(FMLPreInitializationEvent event)
    {
        configFolder = event.getModConfigurationDirectory();
        configFileMain = new File(configFolder.getAbsolutePath() + "/FloraSoma/FloraSoma.cfg");
        configMain = new Configuration(configFileMain);
        updateConfig();
    }

    public static void updateConfig()
    {
        enabledCustomVillageGeneration = configMain.get(CATG, "enabled", true, "Should the custom generator be injected? (Enables/Disables custom village generation)");
        villageSpawnDensity = configMain.get(CATG, "density", 32, "Minecraft will try to generate 1 village per NxN chunk area. \nDefault: 32");
        minimumVillageDistance = configMain.get(CATG, "minimumDistance", 8, "Village centers will be at least N chunks apart. Must be smaller than density. \nDefault: 8");
        villageSize = configMain.get(CATG, "size", 0, "A higher size increases the overall spawn weight of buildings. (Don't ask, I have no idea) \nDefault: 0");

        genCorruption = configMain.get(CATG, "Generate Nether Corrupter", true).getBoolean(true);
        corruptionSpread = configMain.get(CATG, "Corruption Spread", true).getBoolean(true);
        corrupterRarity = configMain.get(CATG, "Corruption Rarity", 8).getInt(8);
        corrupterVeinSize = configMain.get(CATG, "Corruption Vein Size", 16).getInt(16);

        if (minimumVillageDistance.getInt() < 0)
        {
            FloraSoma.instance.log.fatal("Invalid config: Minimal distance must be non-negative.");
            enabledCustomVillageGeneration.set(false);
        }
        if (minimumVillageDistance.getInt() >= villageSpawnDensity.getInt())
        {
            FloraSoma.instance.log.fatal("Invalid config: Minimal distance must be smaller than density.");
            enabledCustomVillageGeneration.set(false);
        }
        if (villageSize.getInt() < 0)
        {
            FloraSoma.instance.log.fatal("Invalid config: Size must be non-negative.");
            enabledCustomVillageGeneration.set(false);
        }

        if (configMain.hasChanged())
            configMain.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (FloraSoma.MOD_ID.equals(event.modID))
            updateConfig();
    }
}
