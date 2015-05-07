package florasoma.world.gen;

import florasoma.FloraSoma;
import florasoma.common.ConfigMain;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

public class VillageGenReplacer
{
    @SubscribeEvent
    public void setVillages(InitMapGenEvent event)
    {
            if (event.type == InitMapGenEvent.EventType.VILLAGE)
            {
                if (!(event.newGen == event.originalGen))
                {
                    FloraSoma.instance.log.fatal("The village map generator was overwritten by another mod. There might be crashes! \n The new generator class is " + event.getClass().getCanonicalName());
                }

                try
                {
                    Field type = null;
                    Field density = null;
                    Field minDist = null;

                    Field[] fields = event.newGen.getClass().getDeclaredFields();
                    for (Field f : fields)
                    {
                        String name = f.getName();
                        if (name.equals("terrainType"))
                        {
                            type = f;
                        } else if (name.equals("field_82665_g"))
                        {
                            density = f;
                        } else if (name.equals("field_82666_h"))
                        {
                            minDist = f;
                        }
                    }

                    if (type != null)
                    {
                        type.setAccessible(true);
                        type.setInt(event.newGen, ConfigMain.villageSize.getInt(0));
                    }
                    if (density != null)
                    {
                        density.setAccessible(true);
                        density.setInt(event.newGen, ConfigMain.villageSpawnDensity.getInt(32));
                    }
                    if (minDist != null)
                    {
                        minDist.setAccessible(true);
                        minDist.setInt(event.newGen, ConfigMain.minimumVillageDistance.getInt(8));
                    }
                    FloraSoma.instance.log.info("Modified MapGenVillage fields.");
                } catch (Exception exc)
                {
                    FloraSoma.instance.log.fatal("Could not modify MapGenVillage, consider disabling Village Density in FloraSoma.cfg");
                    exc.printStackTrace();
                }
            }
        }

    }
