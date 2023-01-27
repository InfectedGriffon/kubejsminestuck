package com.havingfunrightnow.kubejsminestuck.recipehandlers;

import com.google.gson.*;
import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.alchemy.GristAmount;

import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.ListJS;

public class GristCostRecipeJS extends RecipeJS {

    public JsonObject grist_cost = new JsonObject();

    @Override
    public void create(ListJS args) {
        inputItems.add(parseIngredientItem(args.get(0)));
        if (args.get(1) instanceof GristAmount grist) {
            grist_cost.addProperty(grist.getType().toString(),grist.getAmount());
        } else if (args.size() % 2 == 1) {
            var iter = args.iterator();
            iter.next();
            while (iter.hasNext()) {
                var key = iter.next().toString();
                grist_cost.addProperty(
                    key.indexOf(':')>=0?key:Minestuck.MOD_ID+":"+key,
                    Long.parseLong(iter.next().toString().replace(".0", ""))
                );
            }
        } else {
            throw new IllegalArgumentException("Invalid Grist Cost Argument!");
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