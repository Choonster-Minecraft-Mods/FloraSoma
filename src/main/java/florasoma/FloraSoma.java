package florasoma;

import com.google.common.collect.Lists;
import florasoma.common.Config;
import florasoma.util.Tab;
import florasoma.proxy.FloraSomaProxy;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = FloraSoma.MOD_ID, name = FloraSoma.NAME, version = FloraSoma.VERSION)
public class FloraSoma
{
    public static final String MOD_ID = "Florasoma";
    public static final String NAME = "Flora & Soma";
    public static final String VERSION = "1.0.0";


    public static final String CLIENT_SIDE = "florasoma.proxy.FloraSomaProxyClient";
    public static final String SERVER_SIDE = "florasoma.proxy.FloraSomaProxyServer";

    public static final String RSC_PRE = MOD_ID + ":";
    public static int ORE_WEIGHT = 20;
    
    @Mod.Instance(FloraSoma.MOD_ID)
    public static FloraSoma instance;

    @SidedProxy(clientSide = FloraSoma.CLIENT_SIDE, serverSide = FloraSoma.SERVER_SIDE)
    static FloraSomaProxy proxy;

    public static ModMetadata metadata;

    public static Tab tab = new Tab("florasoma");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws Exception
    {
        metadata = event.getModMetadata();

        metadata.modId = FloraSoma.MOD_ID;
        metadata.name = FloraSoma.NAME;
        metadata.version = FloraSoma.VERSION;
        metadata.description = "";
        metadata.url = "";
        metadata.updateUrl = "";
        metadata.authorList = Lists.newArrayList(new String[]{"LogicTechCorp"});
        metadata.logoFile = "assets/inficraft/InfiCraft Logo.png";

        Config.initConfig(event);
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(new Config());
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }

}
