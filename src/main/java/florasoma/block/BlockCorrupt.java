package florasoma.block;

import florasoma.common.ConfigMain;
import florasoma.util.EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockCorrupt extends BlockFloraSoma
{
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumType.Corrupt.class);

    public BlockCorrupt(Material material, String name, float hardness, float resistance, CreativeTabs tab, SoundType sound)
    {
        super(material, name, hardness, resistance, tab, sound);
        this.setTickRandomly(true);
    }

    public int tickRate(World world)
    {
        return (int) 2.5;
    }

    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isRemote)
        {
            if (ConfigMain.corruptionSpread)
            {
                if (this.getBlockState().getBlock() == BlocksFS.corrupter)
                {

                    BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                    IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

                    if (iblockstate1.getBlock() == Blocks.netherrack)
                    {
                        worldIn.setBlockState(blockpos1, this.getActualState(state, worldIn, pos));
                    }
                }
            }
        }
    }

    public boolean isFireSource(World world, BlockPos pos, EnumFacing side)
    {
        return this == BlocksFS.corrupter && side == EnumFacing.UP;
    }


    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        EnumType.Corrupt[] type = EnumType.Corrupt.values();
        int i = type.length;

        for (int j = 0; j < i; ++j)
        {
            EnumType.Corrupt enumMarbleColor = type[j];
            list.add(new ItemStack(item, 1, enumMarbleColor.getMetadata(j)));
        }
    }

    @Override
    public final IBlockState getStateFromMeta(int meta)
    {
        return addVariant(this.getDefaultState(), EnumType.Corrupt.fromMetadata(meta));
    }

    @Override
    public final int getMetaFromState(IBlockState state)
    {
        EnumType.Corrupt value = (EnumType.Corrupt) state.getValue(TYPE);
        return value.ordinal();
    }

    @Override
    public final int damageDropped(IBlockState state)
    {
        EnumType.Corrupt value = (EnumType.Corrupt) state.getValue(TYPE);
        return value.ordinal();
    }

    @Override
    protected final BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[]{TYPE});
    }

    private static IBlockState addVariant(IBlockState baseState, EnumType.Corrupt value)
    {
        return baseState.withProperty(TYPE, value);
    }

}
