package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.api.alchemy.recipe.combination.CombinationMode;
import dev.latvian.mods.kubejs.recipe.component.EnumComponent;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

import static dev.latvian.mods.kubejs.recipe.component.ItemComponents.INPUT;
import static dev.latvian.mods.kubejs.recipe.component.ItemComponents.OUTPUT;

public class CombinationRecipeSchema {

    private static final RecipeComponent<CombinationMode> MODE = new EnumComponent<>(
            CombinationMode.class,
            CombinationMode::asString,
            (c, s) -> CombinationMode.fromString(s.replaceAll("\"", ""))
    );

    public static final RecipeSchema SCHEMA = new RecipeSchema(
            INPUT.key("input1"),
            INPUT.key("input2"),
            MODE.key("mode"),
            OUTPUT.key("output")
    );
}
