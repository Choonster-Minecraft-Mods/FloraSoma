package florasoma.proxy;

import florasoma.util.TextureManager;

public class FloraSomaProxyClient extends FloraSomaProxy
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

    }
}
