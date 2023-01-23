package com.havingfunrightnow.kubejsminestuck.recipehandlers;

import com.google.gson.*;
import com.havingfunrightnow.kubejsminestuck.Utils;

import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.ListJS;

public class GristCostRecipeJS extends RecipeJS {

    public JsonElement grist_cost;

    @Override
    public void create(ListJS args) {
        inputItems.add(parseIngredientItem(args.get(0)));

        if (args.size() == 3) {
            var type = Utils.fallbackNamespace(args.get(1).toString());
            grist_cost = Utils.jsonify(type, args.get(2));
        } else if (args.size() == 2) {
            grist_cost = Utils.jsonify(args.get(1));
        } else {
            grist_cost = Utils.jsonifyPairs(args.subList(1, args.getLength()));
        }
    }

    @Override
    public void deserialize() {
        inputItems.add(parseIngredientItem(json.get("ingredient")));
    }

    @Override
    public void serialize() {
        json.add("ingredient", inputItems.get(0).toJson());
        if (grist_cost != null) {
            json.add("grist_cost", grist_cost);
        }
    } 
}