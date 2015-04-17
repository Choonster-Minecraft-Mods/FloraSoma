package florasoma.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class BlockFloraSoma extends Block
{
    public Block block;
    public World world;
    public Random rand = world != null ? (world).rand : RANDOM;


    public BlockFloraSoma(Material material, String name, float hardness, float resistance, CreativeTabs tab, SoundType sound)
    {
        super(material);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setCreativeTab(tab);
        this.setStepSound(sound);
        this.setUnlocalizedName(name);

    }

    public BlockFloraSoma register(String name)
    {
        GameRegistry.registerBlock(this, name);
        return this;
    }

    public BlockFloraSoma register(String name, Class<? extends ItemBlock> itemclass)
    {
        GameRegistry.registerBlock(this, itemclass, name);
        return this;
    }
}
