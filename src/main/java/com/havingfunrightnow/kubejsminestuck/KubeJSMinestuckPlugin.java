package com.havingfunrightnow.kubejsminestuck;

import com.havingfunrightnow.kubejsminestuck.event.MinestuckEvents;
import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.api.alchemy.GristAmount;
import com.mraof.minestuck.api.alchemy.GristSet;
import com.mraof.minestuck.api.alchemy.GristType;
import com.mraof.minestuck.api.alchemy.GristTypes;
import com.mraof.minestuck.computer.editmode.DeployList;
import com.mraof.minestuck.world.lands.LandTypes;
import com.mraof.minestuck.world.lands.terrain.TerrainLandType;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.recipe.schema.minecraft.CookingRecipeSchema;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.AttachedData;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {
    public static final RegistryInfo<GristType> GRIST = RegistryInfo.of(GristTypes.REGISTRY_KEY, GristType.class);
    public static final RegistryInfo<TerrainLandType> TERRAIN_LAND_TYPE = RegistryInfo.of(LandTypes.TERRAIN_KEY, TerrainLandType.class);

    @Override
    public void init() {
        GRIST.addType("basic", GristBuilder.class, GristBuilder::new);
        TERRAIN_LAND_TYPE.addType("basic", TerrainLandTypeBuilder.class, TerrainLandTypeBuilder::new);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.namespace(Minestuck.MOD_ID)
            .register("combination", KJSMRecipeSchemas.COMBINATION_SCHEMA)
            .register("grist_cost", KJSMRecipeSchemas.GRIST_COST_SCHEMA)
            .register("wildcard_grist_cost", KJSMRecipeSchemas.WILDCARD_GRIST_COST_SCHEMA)
            .register("unavailable_grist_cost", KJSMRecipeSchemas.UNAVAILABLE_GRIST_COST_SCHEMA)
            .register("irradiating", CookingRecipeSchema.SCHEMA)
        ;
    }

    @Override
    public void registerEvents() {
        MinestuckEvents.GROUP.register();
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("GristSet", GristSet.class);
        event.add("GristAmount", GristAmount.class);
        event.add("DeployList", DeployList.class);
    }

    @Override
    public void attachPlayerData(AttachedData<Player> event) {
        event.add(Minestuck.MOD_ID, new KJSMPlayerData((ServerPlayer) event.getParent()));
    }

    @Override
    public void registerTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.registerSimple(GristType.class, o -> getGrist(o.toString()));
    }

    public static GristType getGrist(String id) {
        return GristTypes.getRegistry().getValue(new ResourceLocation(id));
    }
}
