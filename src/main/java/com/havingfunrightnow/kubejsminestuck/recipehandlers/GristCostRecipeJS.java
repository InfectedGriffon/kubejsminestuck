package com.havingfunrightnow.kubejsminestuck.recipehandlers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mraof.minestuck.alchemy.GristAmount;
import com.mraof.minestuck.alchemy.GristSet;
import com.mraof.minestuck.alchemy.GristTypes;

import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.ListJS;

public class GristCostRecipeJS extends RecipeJS {

    public JsonElement grist_cost;

    @Override
    public void create(ListJS args) {
        inputItems.add(parseIngredientItem(args.get(0)));

        if (args.size() == 3) {
            grist_cost = jsonify(args.get(1), args.get(2));
        } else if (args.size() == 2) {
            grist_cost = jsonify(args.get(1));
        } else {
            ConsoleJS.SERVER.error("Too many arguments! Try using Grist.set()!");
        }
    }

    /*
     * take some object and turns it into a json element
     */
    public JsonElement jsonify(Object thisThing) {
        if (thisThing instanceof GristAmount grist) {
            if (grist.getType() == GristTypes.ZILLIUM.get() && grist.getAmount() == Long.MIN_VALUE) {
                return JsonParser.parseString("{}");
            }
            return jsonify(grist.getType(), grist.getAmount());
        } else if (thisThing instanceof GristSet gristSet) { //gristset has built-in serializer
            return gristSet.serialize();
        } else {
            return new Gson().toJsonTree(thisThing);
        }
    }

    /*
     * takes two objects and turns them into a json element
     */
    public JsonElement jsonify(Object key, Object value) {
        return JsonParser.parseString("{\""+key.toString()+"\":"+value.toString()+"}");
    }

    @Override
    public void deserialize() {
        inputItems.add(parseIngredientItem(json.get("ingredient")));
    }

    @Override
    public void serialize() {
        json.add("ingredient", inputItems.get(0).toJson());
        if(grist_cost!=null) { // when no costs (free)
            json.add("grist_cost", grist_cost);
        }
    } 
}