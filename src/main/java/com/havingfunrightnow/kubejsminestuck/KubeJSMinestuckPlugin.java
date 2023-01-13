package com.havingfunrightnow.kubejsminestuck;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeHandlersEvent;
import net.minecraft.resources.ResourceLocation;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {

    public void addRecipes(RegisterRecipeHandlersEvent event) {
        event.register(new ResourceLocation("minestuck:grist_cost"), GristCostRecipeJS::new);
        event.register(new ResourceLocation("minestuck:combination"), CombinationRecipeJS::new);
    }
}
