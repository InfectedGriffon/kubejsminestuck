package com.havingfunrightnow.kubejsminestuck;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeHandlersEvent;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import com.havingfunrightnow.kubejsminestuck.recipehandlers.*;
import com.havingfunrightnow.kubejsminestuck.wrappers.*;
import com.mraof.minestuck.alchemy.*;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {

    private static final ResourceKey<Registry<GristType>> GRIST_REGISTRY_NAME = ResourceKey.createRegistryKey(GristTypes.GRIST_TYPES.getRegistryName());

    public static final RegistryObjectBuilderTypes<GristType> GRIST = RegistryObjectBuilderTypes.add(GRIST_REGISTRY_NAME, GristType.class);

    @Override
    public void addRecipes(RegisterRecipeHandlersEvent event) {
        event.register(new ResourceLocation("minestuck:grist_cost"), GristCostRecipeJS::new);
        event.register(new ResourceLocation("minestuck:combination"), CombinationRecipeJS::new);
        event.register(new ResourceLocation("minestuck:irradiating"), IrradiatingRecipeJS::new);
    }
    
    @Override
    public void init() {
        GRIST.addType("basic", KubeJSGristBuilder.class, KubeJSGristBuilder::new);
    }
    
    @Override
    public void addBindings(BindingsEvent event) {
        event.add("Grist", GristWrapper.class);
        event.add("GristSet", GristSetWrapper.class);
    }

    @Override
	public void addTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.register(GristType.class, g -> getGrist(g));
        typeWrappers.register(GristAmount.class, g -> getGristAmount(g));
    }

    public GristType getGrist(Object g) {
        return getGrist(g, true);
    }

    public GristType getGrist(Object g, boolean useFallbackNamespace) {
        if (g instanceof GristType grist) {return grist;} //return self when grist
        var stringified = g.toString();
        if (!stringified.contains(":") && useFallbackNamespace) { //default to minestuck when no namespace
            stringified = "minestuck:" + stringified;
        }
        var type = GristTypes.getRegistry().getValue(new ResourceLocation(stringified));
        if(type==null) {
            ConsoleJS.SERVER.error("Invalid Grist Type: "+stringified+"!");
        }
        return type;
    }

    public GristAmount getGristAmount(Object g) {
        if (g instanceof GristAmount grist) {return grist;}
        String[] s = g
            .toString()
            .replaceAll("\\{(.*)\\}","$1")
            .split(":");
        GristType type = getGrist(s[0]+":"+s[1], false);
        Long amount = Long.parseLong(s[2].replaceAll("\\s|\\..*",""));
        return new GristAmount(type, amount);
    }
}
