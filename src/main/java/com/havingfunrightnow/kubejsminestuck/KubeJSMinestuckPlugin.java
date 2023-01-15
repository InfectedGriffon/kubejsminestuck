package com.havingfunrightnow.kubejsminestuck;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeHandlersEvent;
import net.minecraft.resources.ResourceLocation;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import com.havingfunrightnow.kubejsminestuck.recipehandlers.CombinationRecipeJS;
import com.havingfunrightnow.kubejsminestuck.recipehandlers.GristCostRecipeJS;
import com.havingfunrightnow.kubejsminestuck.recipehandlers.IrradiatingRecipeJS;
import com.havingfunrightnow.kubejsminestuck.wrappers.GristSetWrapper;
import com.havingfunrightnow.kubejsminestuck.wrappers.GristWrapper;
import com.mraof.minestuck.alchemy.GristSet;
import com.mraof.minestuck.alchemy.GristType;
import com.mraof.minestuck.alchemy.GristTypes;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {

    public static final RegistryObjectBuilderTypes<GristType> GRIST = RegistryObjectBuilderTypes.add(UtilsJS.cast(GristTypes.getRegistry()), GristType.class);

    @Override
    public void addRecipes(RegisterRecipeHandlersEvent event) {
        event.register(new ResourceLocation("minestuck:grist_cost"), GristCostRecipeJS::new);
        event.register(new ResourceLocation("minestuck:combination"), CombinationRecipeJS::new);
        event.register(new ResourceLocation("minestuck:irradiating"), IrradiatingRecipeJS::new);
    }

    @Override
    public void addBindings(BindingsEvent event) {
        event.add("Grist", GristWrapper.class);
        event.add("GristSet", GristSetWrapper.class);
    }

    @Override
	public void addTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.register(GristType.class, g -> getGrist(g));
        typeWrappers.register(GristSet.class, g -> getGristSet(g));
    }
    public GristType getGrist(Object g) {
        if (g instanceof GristType grist) {return grist;} //return self when grist
        var stringified = g.toString();
        if (!stringified.contains(":")) { //default to minestuck when no namespace
            stringified = "minestuck:" + stringified;
        }
        var type = GristTypes.getRegistry().getValue(new ResourceLocation(stringified));
        if(type==null) {
            ConsoleJS.SERVER.error("Invalid Grist Type: "+stringified+"!");
        }
        return type;
    }
    public GristSet getGristSet(Object g) {
        if (g instanceof GristSet grist) {return grist;}
        ConsoleJS.SERVER.info(g);
        return new GristSet();
    }
}
