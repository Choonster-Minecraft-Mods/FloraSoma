package florasoma;

import com.google.common.collect.Lists;
import florasoma.common.ConfigHandler;
import florasoma.common.ConfigMain;
import florasoma.util.Tab;
import florasoma.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Field;

@Mod(modid = FloraSoma.MOD_ID, name = FloraSoma.NAME, version = FloraSoma.VERSION)
public class FloraSoma
{
    public static final String MOD_ID = "FloraSoma";
    public static final String NAME = "Flora & Soma";
    public static final String VERSION = "1.1.0";

    public static final String CLIENT_SIDE = "florasoma.proxy.ClientProxy";
    public static final String SERVER_SIDE = "florasoma.proxy.ServerProxy";

    public static final String RSC_PRE = MOD_ID + ":";
    public static int ORE_WEIGHT = 20;
    
    @Mod.Instance(FloraSoma.MOD_ID)
    public static FloraSoma instance;

    public Logger log = LogManager.getLogger(FloraSoma.class);

    @SidedProxy(clientSide = FloraSoma.CLIENT_SIDE, serverSide = FloraSoma.SERVER_SIDE)
    static CommonProxy proxy;

    public static ModMetadata metadata;

    public static Tab tab = new Tab("florasoma");

    private static File file;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        metadata = event.getModMetadata();

        metadata.modId = FloraSoma.MOD_ID;
        metadata.name = FloraSoma.NAME;
        metadata.version = FloraSoma.VERSION;
        metadata.description = "";
        metadata.url = "";
        metadata.updateUrl = "";
        metadata.authorList = Lists.newArrayList("LogicTechCorp");

        file = new File(event.getModConfigurationDirectory(), "/FloraSoma/VillageBiomes.cfg");

        ConfigMain.initConfig(event);
        ConfigHandler.loadConfig(file);
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }

}
