package florasoma.util;

import net.minecraft.block.state.IBlockState;

public class Pair<Left, Right>
{
    public final Left left;
    public final Right right;

    public Pair(Left left, Right right)
    {
        this.left = left;
        this.right = right;
    }
}
