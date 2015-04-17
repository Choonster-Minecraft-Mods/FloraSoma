package florasoma.common;

import florasoma.FloraSoma;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityReg
{
    private static void registerMonster(Class entityClass, String entityName, BiomeGenBase genBase, int solidColor, int spotColor)
    {
        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
        EntityRegistry.registerModEntity(entityClass, entityName, id, FloraSoma.instance, 64, 1, true);
        EntityRegistry.addSpawn(entityClass, 0, 0, 0, EnumCreatureType.MONSTER, genBase);

        registerEgg(id, solidColor, spotColor);
    }

    private static void registerCreature(Class entityClass, String entityName, BiomeGenBase genBase, int solidColor, int spotColor)
    {
        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
        EntityRegistry.registerModEntity(entityClass, entityName, id, FloraSoma.instance, 64, 1, true);
        EntityRegistry.addSpawn(entityClass, 0, 0, 0, EnumCreatureType.CREATURE, genBase);

        registerEgg(id, solidColor, spotColor);
    }

    private static void registerWaterCreature(Class entityClass, String entityName, BiomeGenBase genBase, int solidColor, int spotColor)
    {
        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
        EntityRegistry.registerModEntity(entityClass, entityName, id, FloraSoma.instance, 64, 1, true);
        EntityRegistry.addSpawn(entityClass, 0, 0, 0, EnumCreatureType.WATER_CREATURE, genBase);

        registerEgg(id, solidColor, spotColor);
    }

    private static void registerAmbient(Class entityClass, String entityName, BiomeGenBase genBase, int solidColor, int spotColor)
    {
        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
        EntityRegistry.registerModEntity(entityClass, entityName, id, FloraSoma.instance, 64, 1, true);
        EntityRegistry.addSpawn(entityClass, 0, 0, 0, EnumCreatureType.AMBIENT, genBase);

        registerEgg(id, solidColor, spotColor);
    }



    private static void registerEgg(int id, int solidColor, int spotColor)
    {
        EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, solidColor, spotColor));
    }
}
