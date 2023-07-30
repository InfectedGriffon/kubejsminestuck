package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.alchemy.GristType;
import com.mraof.minestuck.alchemy.GristTypes;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import dev.latvian.mods.kubejs.util.TinyMap;

public class GristCostRecipeSchema {

    private static final RecipeKey<InputItem> INGREDIENT = ItemComponents.INPUT.key("ingredient");

    private static final RecipeKey<TinyMap<GristType, Long>> GRIST_COSTS = new MapRecipeComponent<>(
            new RegistryComponent<>(GristTypes.getRegistry().getRegistryKey(), GristType.class),
            NumberComponent.ANY_LONG,
            true
    ).key("grist_cost").allowEmpty();

    public static final RecipeSchema SCHEMA = new RecipeSchema(INGREDIENT, GRIST_COSTS).uniqueInputId(INGREDIENT);
}
