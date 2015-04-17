package florasoma;


import florasoma.block.BlocksFS;
import florasoma.util.EnumType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes
{
    public static void register()
    {
        for(int i = 0; i < EnumType.Corrupt.values().length; i ++)
        {
            GameRegistry.addRecipe(new ItemStack(BlocksFS.corruptBrick, 4, i), "SS", "SS", 'S', new ItemStack(BlocksFS.corruptStone, 1, i));
            GameRegistry.addSmelting(new ItemStack(BlocksFS.corrupter, 1, i), new ItemStack(BlocksFS.corruptStone, 1, i), 1.5F);
        }
    }
}
