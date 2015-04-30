package florasoma.world.gen;

import florasoma.common.ConfigMain;
import florasoma.block.BlocksFS;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenNether implements IWorldGenerator
{
    WorldGenMinableMeta corrupter;

    public WorldGenNether()
    {
        corrupter = new WorldGenMinableMeta(BlocksFS.corrupter.getDefaultState(), 8, ConfigMain.corrupterVeinSize, Blocks.netherrack);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if(world.provider.getDimensionId() == -1)
        {
            if (ConfigMain.genCorruption)
                generateCorrupter(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    public void generateCorrupter(World world, Random rand, int chunkX, int chunkZ)
    {
        for (int i = 0; i < ConfigMain.corrupterRarity; i++)
        {
            int hi = rand.nextInt(256);
            int randX = chunkX + rand.nextInt(16);
            int randZ = chunkZ + rand.nextInt(16);
            System.out.println("Generating Corrupter at x: " + randX + " y: " + hi + " z: " + randZ);
            corrupter.generate(world, rand, new BlockPos(randX, hi, randZ));
        }
    }
}
