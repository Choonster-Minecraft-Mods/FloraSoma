package florasoma.world.gen;

import florasoma.FloraSoma;
import florasoma.common.ConfigMain;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.IEventListener;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

public class VillageGenReplacer implements IEventListener
{

    @Override
    @SubscribeEvent
    public void invoke(Event event)
    {
        if (event instanceof InitMapGenEvent)
        {
            InitMapGenEvent e = (InitMapGenEvent) event;
            if (e.type == InitMapGenEvent.EventType.VILLAGE)
            {
                if (!(e.newGen == e.originalGen))
                {
                    FloraSoma.instance.log.fatal("The village map generator was overwritten by another mod. There might be crashes! \n The new generator class is " + e.getClass().getCanonicalName());
                }

                try
                {
                    Field type = null;
                    Field density = null;
                    Field minDist = null;

                    Field[] fields = e.newGen.getClass().getDeclaredFields();
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
                        type.setInt(e.newGen, ConfigMain.villageSize.getInt(0));
                    }
                    if (density != null)
                    {
                        density.setAccessible(true);
                        density.setInt(e.newGen, ConfigMain.villageSpawnDensity.getInt(32));
                    }
                    if (minDist != null)
                    {
                        minDist.setAccessible(true);
                        minDist.setInt(e.newGen, ConfigMain.minimumVillageDistance.getInt(8));
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

}
