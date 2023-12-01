package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.api.alchemy.GristType;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.util.TinyMap;

public class GristCostRecipeSchema {

    private static final RecipeKey<InputItem> INGREDIENT = ItemComponents.INPUT.key("ingredient");

    private static final RecipeKey<TinyMap<GristType, Long>> GRIST_COSTS = new MapRecipeComponent<>(
            new RegistryComponent<>(KubeJSMinestuckPlugin.GRIST),
            NumberComponent.ANY_LONG,
            true
    ).key("grist_cost").allowEmpty();

    private static final RecipeKey<Integer> PRIORITY = NumberComponent.ANY_INT.key("priority").defaultOptional();

    private static final RecipeKey<Long> WILDCARD_AMOUNT = NumberComponent.ANY_LONG.key("grist_cost");

    public static final RecipeSchema GRIST_COST_SCHEMA = new RecipeSchema(INGREDIENT, GRIST_COSTS, PRIORITY).uniqueInputId(INGREDIENT);
    public static final RecipeSchema WILDCARD_GRIST_COST_SCHEMA = new RecipeSchema(INGREDIENT, WILDCARD_AMOUNT, PRIORITY).uniqueInputId(INGREDIENT);
    public static final RecipeSchema UNAVAILABLE_GRIST_COST_SCHEMA = new RecipeSchema(INGREDIENT, PRIORITY).uniqueInputId(INGREDIENT);
}
