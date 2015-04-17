package florasoma.world.gen;

import com.google.common.base.Predicate;
import florasoma.FloraSoma;
import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class OreGen implements IWorldGenerator
{

    private final float frequency;
    private final WorldGenMinable oreGen;
    private final int maxY;
    private final long hash;

    private final Block ore;

    public OreGen(Block oreBlock, int maxHeight, float spawnFrequency, int spawnQuantity, long hash, Predicate target)
    {
        oreGen = new WorldGenMinable(oreBlock.getDefaultState(), spawnQuantity, target);
        frequency = spawnFrequency;
        maxY = maxHeight;
        ore = oreBlock;
        this.hash = hash;
    }

    public OreGen(Block oreBlock, int meta, int maxHeight, float spawnFrequency, int spawnQuantity, long hash, Predicate target)
    {
        oreGen = new WorldGenMinable(oreBlock.getStateFromMeta(meta), spawnQuantity, target);
        frequency = spawnFrequency;
        maxY = maxHeight;
        ore = oreBlock;
        this.hash = hash;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        random.setSeed(random.nextLong() ^ hash);
        random.nextInt();
        final float r = random.nextFloat();
        for (float f = frequency; f > r; f -= 1)
        {
            int x = (chunkX << 4) + random.nextInt(16);
            int z = (chunkZ << 4) + random.nextInt(16);

            if(maxY == 16)
            {
                int y = random.nextInt(maxY - 0) + 0;
                oreGen.generate(world, random, new BlockPos(x, y, z));
            }

            if(maxY == 32)
            {
                int y = random.nextInt(maxY - 17) + 17;
                oreGen.generate(world, random, new BlockPos(x, y, z));
            }

            if(maxY == 48)
            {
                int y = random.nextInt(maxY - 33) + 33;
                oreGen.generate(world, random, new BlockPos(x, y, z));
            }

            if(maxY == 64)
            {
                int y = random.nextInt(maxY - 49) + 49;
                oreGen.generate(world, random, new BlockPos(x, y, z));
            }

            if(maxY == 256)
            {
                int y = random.nextInt(maxY - 65) + 65;
                oreGen.generate(world, random, new BlockPos(x, y, z));
            }
        }
    }

    public static void addOre(Block oreBlock, int meta, int maxY, float spawnFrequency, int spawnQuantity, Block block)
    {
        GameRegistry.registerWorldGenerator(new OreGen(oreBlock, meta, maxY, spawnFrequency, spawnQuantity, (FloraSoma.ORE_WEIGHT * 25214903917L) + 11L, BlockHelper.forBlock(block)), FloraSoma.ORE_WEIGHT++);
    }

    public static void addOre(Block oreBlock, int meta, float spawnFrequency, int spawnQuantity, Block block)
    {
        GameRegistry.registerWorldGenerator(new OreGen(oreBlock, meta, spawnFrequency, spawnQuantity, (FloraSoma.ORE_WEIGHT * 25214903917L) + 11L, BlockHelper.forBlock(block)), FloraSoma.ORE_WEIGHT++);
    }
}
