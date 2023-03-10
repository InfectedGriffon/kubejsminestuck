package com.havingfunrightnow.kubejsminestuck;

import com.havingfunrightnow.kubejsminestuck.recipehandlers.CombinationRecipeJS;
import com.havingfunrightnow.kubejsminestuck.recipehandlers.GristCostRecipeJS;
import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.alchemy.GristType;
import com.mraof.minestuck.alchemy.GristTypes;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;
import dev.latvian.mods.kubejs.recipe.minecraft.CookingRecipeJS;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class KubeJSMinestuckPlugin extends KubeJSPlugin {

    private static final ResourceKey<Registry<GristType>> GRIST_REGISTRY_NAME = 
        ResourceKey.createRegistryKey(GristTypes.GRIST_TYPES.getRegistryName());

    public static final RegistryObjectBuilderTypes<GristType> GRIST = 
        RegistryObjectBuilderTypes.add(GRIST_REGISTRY_NAME, GristType.class);

    @Override
    public void init() {
        GRIST.addType("basic", GristBuilder.class, GristBuilder::new);
    }

    @Override
    public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
        event.register(new ResourceLocation(Minestuck.MOD_ID, "grist_cost"), GristCostRecipeJS::new);
        event.register(new ResourceLocation(Minestuck.MOD_ID, "combination"), CombinationRecipeJS::new);
        event.register(new ResourceLocation(Minestuck.MOD_ID, "irradiating"), CookingRecipeJS::new);
    }

    @Override
    public void registerTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.registerSimple(GristType.class, KubeJSMinestuckPlugin::getGristType);
    }

    public static GristType getGristType(Object o) {
        if (o instanceof GristType type) {
            return type;
        }

        if (o instanceof ResourceLocation id) {
            var type = GristTypes.getRegistry().getValue(id);

            if (type != null) {
                return type;
            }
        }

        if (o instanceof String str) {
            return getGristType(new ResourceLocation(str.indexOf(':')>=0? str : Minestuck.MOD_ID+":"+str));
        }

        ConsoleJS.SERVER.error("Unknown grist type: " + o);
        return null;
    }
}
