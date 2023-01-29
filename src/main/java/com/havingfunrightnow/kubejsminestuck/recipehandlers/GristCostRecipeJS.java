package com.havingfunrightnow.kubejsminestuck.recipehandlers;

import com.google.gson.JsonObject;
import com.mraof.minestuck.Minestuck;

import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.ListJS;

public class GristCostRecipeJS extends RecipeJS {

    public JsonObject grist_cost = new JsonObject();

    @Override
    public void create(ListJS args) {
        inputItems.add(parseIngredientItem(args.get(0)));
        if (args.size() == 1) {
            return; //free
        } else if (args.size() % 2 == 0) {
            throw new IllegalArgumentException("Invalid Grist Cost Argument! Need pairs of names and amounts!");
        }
        
        var iter = args.iterator();
        iter.next(); //skip over item
        while (iter.hasNext()) {
            var key = iter.next().toString();
            grist_cost.addProperty(
                key.indexOf(":")>=0?key:Minestuck.MOD_ID+":"+key,
                jsNumToLong(iter.next().toString())
            );
        }
    }

    private Long jsNumToLong(String s) {
        // scientific notation
        if (s.indexOf("E")>=0) {
            return ((Double) Double.parseDouble(s)).longValue();
        }
        // every other number (will error on decimals)
        return Long.parseLong(s.replace(".0",""));
    }

    @Override
    public void deserialize() {
        inputItems.add(parseIngredientItem(json.get("ingredient")));
        grist_cost = json.get("grist_cost").getAsJsonObject();
    }

    @Override
    public void serialize() {
        json.add("ingredient", inputItems.get(0).toJson());
        json.add("grist_cost", grist_cost);
    }
}