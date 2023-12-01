package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.api.alchemy.GristType;
import com.mraof.minestuck.api.alchemy.GristTypes;
import com.mraof.minestuck.item.crafting.MSRecipeTypes;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.recipe.schema.minecraft.CookingRecipeSchema;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.AttachedData;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {
    public static final RegistryInfo<GristType> GRIST = RegistryInfo.of(GristTypes.REGISTRY_KEY, GristType.class);

    @Override
    public void init() {
        GRIST.addType("basic", GristBuilder.class, GristBuilder::new);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.register(MSRecipeTypes.COMBINATION_TYPE.getId(), CombinationRecipeSchema.SCHEMA);
        event.register(MSRecipeTypes.GRIST_COST_TYPE.getId(), GristCostRecipeSchema.GRIST_COST_SCHEMA);
        event.register(MSRecipeTypes.WILDCARD_GRIST_COST.getId(), GristCostRecipeSchema.WILDCARD_GRIST_COST_SCHEMA);
        event.register(MSRecipeTypes.UNAVAILABLE_GRIST_COST.getId(), GristCostRecipeSchema.UNAVAILABLE_GRIST_COST_SCHEMA);
        event.register(MSRecipeTypes.IRRADIATING_TYPE.getId(), CookingRecipeSchema.SCHEMA);
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
