package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.Minestuck;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;
import net.minecraft.resources.ResourceLocation;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {

    @Override
    public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
        event.register(new ResourceLocation(Minestuck.MOD_ID, "combination"), CombinationRecipeJS::new);
        event.register(new ResourceLocation(Minestuck.MOD_ID, "grist_cost"), GristCostRecipeJS::new);
    }
}
