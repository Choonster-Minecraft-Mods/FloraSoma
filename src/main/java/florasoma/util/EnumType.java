package florasoma.util;

import net.minecraft.util.IStringSerializable;

public class EnumType
{
    public static enum Corrupt implements IStringSerializable
    {
        BLOOD(0),
        AZURE(1),
        ENVIOUS(2),
        AMBER(3),
        HEART(4),
        DARK(5),
        BRIGHT(6),
        PURE(7);

        private int metadataValue;

        public static Corrupt fromMetadata(int meta)
        {
            return values()[meta];
        }

        @Override
        public String getName()
        {
            return TypeUtility.Corrupt.TYPE_NAMES[this.metadataValue];
        }

        public int getMetadata(int meta)
        {
            this.metadataValue = meta;

            return meta;
        }

        private Corrupt(int value)
        {
            this.metadataValue = value;
        }
    }

}

