package florasoma.block;

import florasoma.FloraSoma;
import florasoma.item.block.ItemBlockCorrupt;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlocksFS
{
    public static Block corrupter;
    public static Block corruptStone;
    public static Block corruptBrick;

    public static void register()
    {
        corrupter = new BlockCorrupt(Material.rock, "stone.nether.corrupter", 0.4f, 2, FloraSoma.tab, Block.soundTypeStone).register("stone_nether_corrupter", ItemBlockCorrupt.class);
        corruptStone = new BlockCorrupt(Material.rock, "stone.nether.corrupt", 1.5f, 10, FloraSoma.tab, Block.soundTypeStone).register("stone_nether_corrupt", ItemBlockCorrupt.class);
        corruptBrick = new BlockCorrupt(Material.rock, "stone.brick.nether.corrupt", 2f, 10, FloraSoma.tab, Block.soundTypeStone).register("stone_brick_nether_corrupt", ItemBlockCorrupt.class);

    }

}
