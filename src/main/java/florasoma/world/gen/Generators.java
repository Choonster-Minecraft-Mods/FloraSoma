package florasoma.world.gen;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class Generators
{
    public static void register()
    {
        GameRegistry.registerWorldGenerator(new WorldGenNether(), 0);
    }
}
