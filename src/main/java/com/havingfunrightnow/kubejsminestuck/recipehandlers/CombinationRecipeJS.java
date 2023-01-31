package com.havingfunrightnow.kubejsminestuck.recipehandlers;

import dev.latvian.mods.kubejs.util.ListJS;
import com.mraof.minestuck.alchemy.CombinationMode;
import dev.latvian.mods.kubejs.recipe.RecipeJS;

public class CombinationRecipeJS extends RecipeJS {

    // and/or
    public CombinationMode mode;

    @Override
    public void create(ListJS args) {
        outputItems.add(parseResultItem(args.get(0)));
		inputItems.add(parseIngredientItem(args.get(1)));
        mode = CombinationMode.fromString(args.get(2).toString());
		inputItems.add(parseIngredientItem(args.get(3)));
    }

    @Override
    public void deserialize() {
        outputItems.add(parseResultItem(json.get("output")));
		inputItems.add(parseIngredientItem(json.get("input1")));
        inputItems.add(parseIngredientItem(json.get("input2")));
        mode = CombinationMode.fromString(json.get("mode").getAsString());
    }

    @Override
    public void serialize() {
        json.add("output", outputItems.get(0).toResultJson());
        json.add("input1", inputItems.get(0).toJson());
        json.add("input2", inputItems.get(1).toJson());
        json.addProperty("mode", mode.asString());
    }
}
