package com.havingfunrightnow.kubejsminestuck;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeHandlersEvent;
import dev.latvian.mods.kubejs.recipe.minecraft.CookingRecipeJS;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;

import com.havingfunrightnow.kubejsminestuck.recipehandlers.*;
import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.alchemy.*;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {

    // girl help i am going insane
    private static final ResourceKey<Registry<GristType>> GRIST_REGISTRY_NAME = ResourceKey.createRegistryKey(GristTypes.GRIST_TYPES.getRegistryName());
    public static final RegistryObjectBuilderTypes<GristType> GRIST = RegistryObjectBuilderTypes.add(GRIST_REGISTRY_NAME, GristType.class);

    @Override
    public void init() {
        GRIST.addType("basic", GristBuilder.class, GristBuilder::new);
    }

    @Override
    public void addRecipes(RegisterRecipeHandlersEvent event) {
        event.register(new ResourceLocation(Minestuck.MOD_ID, "grist_cost"), GristCostRecipeJS::new);
        event.register(new ResourceLocation(Minestuck.MOD_ID, "combination"), CombinationRecipeJS::new);
        event.register(new ResourceLocation(Minestuck.MOD_ID, "irradiating"), CookingRecipeJS::new);
    }

    @Override
	public void addTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.register(GristType.class, g -> getGrist(g));
    }

    /**
     * defaults usefallbacknamespace to true
     * @param o the object to turn into a grist type
     * @return the grist type parsed
     */
    public static GristType getGrist(Object o) {
        if (o instanceof GristType grist) {return grist;} //return self when grist
        var s = o.toString();
        if (!s.contains(":")) { //default to minestuck when no namespace
            s = "minestuck:" + s;
        }
        var type = GristTypes.getRegistry().getValue(new ResourceLocation(s));
        if(type==null) {
            ConsoleJS.SERVER.error("Invalid Grist Type: "+s+"!");
        }
        return type;
    }
}
