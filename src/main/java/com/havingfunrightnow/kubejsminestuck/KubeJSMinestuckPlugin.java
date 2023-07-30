package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.alchemy.GristType;
import com.mraof.minestuck.alchemy.GristTypes;
import com.mraof.minestuck.item.crafting.MSRecipeTypes;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import dev.latvian.mods.kubejs.recipe.schema.minecraft.CookingRecipeSchema;
import dev.latvian.mods.kubejs.registry.RegistryInfo;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {
    public static final RegistryInfo GRIST = RegistryInfo.of(GristTypes.GRIST_TYPES.getRegistryKey()).type(GristType.class);

    @Override
    public void init() {
        GRIST.addType("basic", GristBuilder.class, GristBuilder::new);
    }

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.register(MSRecipeTypes.COMBINATION_TYPE.getId(), CombinationRecipeSchema.SCHEMA);
        event.register(MSRecipeTypes.GRIST_COST_TYPE.getId(), GristCostRecipeSchema.SCHEMA);
        event.register(MSRecipeTypes.IRRADIATING_TYPE.getId(), CookingRecipeSchema.SCHEMA);
    }
}
