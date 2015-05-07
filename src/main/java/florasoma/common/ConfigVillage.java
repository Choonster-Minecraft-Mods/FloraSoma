package florasoma.common;

import florasoma.FloraSoma;
import florasoma.util.Pair;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigVillage
{

    private static List<String> configLines = new ArrayList<String>();

    public static void loadConfig(File file)
    {
        try
        {
            if (file.createNewFile())
            {
                try
                {
                    InputStream defaultConf = FloraSoma.class.getResourceAsStream("/FloraSoma/VillageBiomes.cfg");

                    FileWriter writer = new FileWriter(file);
                    IOUtils.copy(defaultConf, writer);
                    writer.close();
                    defaultConf.close();

                } catch (Exception e)
                {
                    FloraSoma.instance.log.fatal(e.getMessage());
                    e.printStackTrace();
                }
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine())
            {
                configLines.add(sc.nextLine().trim());
            }
            sc.close();
        } catch (IOException e)
        {
            FMLLog.severe("[%s] Can't load or create its config in %s.",
                    FloraSoma.MOD_ID, file.getAbsolutePath());
        }
    }

    private static List<String> getAfterPrefix(String prefix)
    {
        List<String> result = new ArrayList<String>();
        for (String line : configLines)
        {
            if (line.startsWith(prefix))
                result.add(line.substring(prefix.length()));
        }
        return result;
    }

    public static List<String> getAddBiomes()
    {
        return getAfterPrefix("+b:");
    }

    public static List<String> getAddTypes()
    {
        return getAfterPrefix("+t:");
    }

    public static List<String> getRemoveBiomes()
    {
        return getAfterPrefix("-b:");
    }

    public static List<String> getRemoveTypes()
    {
        return getAfterPrefix("-t:");
    }

    public static Map<Block, List<Pair<String, Block>>> getReplacements()
    {
        Map<Block, List<Pair<String, Block>>> map = new HashMap<Block, List<Pair<String, Block>>>();

        Pattern p = Pattern.compile("^~([\\w:]+),([\\w:]+?)(?::\\d+)?,([bt]:.+)");
        for (String line : configLines)
        {
            Matcher m = p.matcher(line);
            if (m.matches())
            {
                Block b = Block.getBlockFromName(m.group(1));
                Block replacement = Block.getBlockFromName(m.group(2));

                String condition = m.group(3);
                FloraSoma.instance.log.info("Will replace " + b.getUnlocalizedName().substring(5) + " with " + replacement.getUnlocalizedName().substring(5) + " where " + condition);

                if (!map.containsKey(b))
                    map.put(b, new ArrayList<Pair<String, Block>>());
                map.get(b).add(new Pair<String, Block>(condition, replacement));
            }
        }
        return map;
    }

    public static Map<Block, List<Pair<String, Integer>>> getMetadata()
    {
        Map<Block, List<Pair<String, Integer>>> map = new HashMap<Block, List<Pair<String, Integer>>>();

        Pattern p = Pattern.compile("^~([\\w:]+),[\\w:]+?:(\\d+),([bt]:.+)");
        for (String line : configLines)
        {
            Matcher m = p.matcher(line);
            if (m.matches())
            {
                Block b = Block.getBlockFromName(m.group(1));
                Integer meta = Integer.valueOf(m.group(2));
                String condition = m.group(3);

                if (!map.containsKey(b))
                    map.put(b, new ArrayList<Pair<String, Integer>>());
                map.get(b).add(new Pair<String, Integer>(condition, meta));
            }
        }
        return map;
    }
}