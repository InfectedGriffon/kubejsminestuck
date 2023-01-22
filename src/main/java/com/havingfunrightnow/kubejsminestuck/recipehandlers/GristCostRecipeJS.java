package com.havingfunrightnow.kubejsminestuck.recipehandlers;

import com.google.gson.*;
import com.mraof.minestuck.alchemy.*;

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

    
    /**
     * @param someObject some artbitrary object to conver into json
     * @return json element representing said object
     */
    public JsonElement jsonify(Object someObject) {
        if (someObject instanceof GristAmount grist) {
            if (grist.getType() == GristTypes.ZILLIUM.get() && grist.getAmount() == Long.MIN_VALUE) {
                return JsonParser.parseString("{}");
            }
            return jsonify(grist.getType(), grist.getAmount());
        } else if (someObject instanceof GristSet gristSet) { // gristset has built-in serializer
            return gristSet.serialize();
        } else {
            return new Gson().toJsonTree(someObject);
        }
    }

    
    /**
     * @param key part before the colon
     * @param value part after the colon
     * @return json element formatted like {key:value}
     * @throws JsonParseException - if key or value contain colons, commas, etc in string form
     * @throws JsonSyntaxException
     */
    public JsonElement jsonify(Object key, Object value) {
        return JsonParser.parseString("{\"" + key.toString() + "\":" + value.toString() + "}");
    }

    @Override
    public void deserialize() {
        inputItems.add(parseIngredientItem(json.get("ingredient")));
    }

    @Override
    public void serialize() {
        json.add("ingredient", inputItems.get(0).toJson());
        if (grist_cost != null) { // when no costs (free)
            json.add("grist_cost", grist_cost);
        }
    } 
}