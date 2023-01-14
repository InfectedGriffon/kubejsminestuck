package com.havingfunrightnow.kubejsminestuck;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeHandlersEvent;
import net.minecraft.resources.ResourceLocation;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import com.mraof.minestuck.alchemy.GristType;
import com.mraof.minestuck.alchemy.GristTypes;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {

    @Override
    public void addRecipes(RegisterRecipeHandlersEvent event) {
        event.register(new ResourceLocation("minestuck:grist_cost"), GristCostRecipeJS::new);
        event.register(new ResourceLocation("minestuck:combination"), CombinationRecipeJS::new);
        event.register(new ResourceLocation("minestuck:irradiating"), IrradiatingRecipeJS::new);
    }

    @Override
    public void addBindings(BindingsEvent event) {
        event.add("Grist", GristWrapper.class);
    }

    @Override
	public void addTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.register(GristType.class, g -> getGrist(g));
    }
    public GristType getGrist(Object g) {
        if (g instanceof GristType grist) {return grist;} //return self when grist
        var stringified = g.toString();
        if (!stringified.contains(":")) { //default to minestuck when no namespace
            stringified = "minestuck:" + stringified;
        }
        return GristTypes.getRegistry().getValue(new ResourceLocation(stringified));
    }
}
