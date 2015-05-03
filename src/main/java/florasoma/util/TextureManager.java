package florasoma.util;

import florasoma.FloraSoma;
import florasoma.block.BlocksFS;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class TextureManager
{
    public static void registerTextures()
    {
        for (int i = 0; i < EnumType.Corrupt.values().length; i++)
        {
            registerBlock(BlocksFS.corrupter, i, EnumType.Corrupt.fromMetadata(i).getName() + "_corrupter");
            registerBlock(BlocksFS.corruptStone, i, EnumType.Corrupt.fromMetadata(i).getName() + "_stone");
            registerBlock(BlocksFS.corruptBrick, i, EnumType.Corrupt.fromMetadata(i).getName() + "_brick");
        }
    }

    public static void registerVariants()
    {
        for (int i = 0; i < EnumType.Corrupt.values().length; i++)
        {
            addVariant(BlocksFS.corrupter, new String[]{FloraSoma.RSC_PRE + EnumType.Corrupt.fromMetadata(i).getName() + "_corrupter"});
            addVariant(BlocksFS.corruptStone, new String[]{FloraSoma.RSC_PRE + EnumType.Corrupt.fromMetadata(i).getName() + "_stone"});
            addVariant(BlocksFS.corruptBrick, new String[]{FloraSoma.RSC_PRE + EnumType.Corrupt.fromMetadata(i).getName() + "_brick"});

        }
    }

    public static void registerItem(Item item, int metadata, String itemName)
    {
        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        mesher.register(item, metadata, new ModelResourceLocation(FloraSoma.RSC_PRE + itemName, "inventory"));
    }

    public static void registerBlock(Block block, int metadata, String blockName)
    {
        registerItem(Item.getItemFromBlock(block), metadata, blockName);
    }

    public static void registerBlock(Block block, String blockName)
    {
        registerBlock(block, 0, blockName);
    }

    public static void registerItem(Item item, String itemName)
    {
        registerItem(item, 0, itemName);
    }

    public static void addVariant(Item item, String[] strings)
    {
        ModelBakery.addVariantName(item, strings);
    }

    public static void addVariant(Block block, String[] strings)
    {
        ModelBakery.addVariantName(Item.getItemFromBlock(block), strings);
    }
}
