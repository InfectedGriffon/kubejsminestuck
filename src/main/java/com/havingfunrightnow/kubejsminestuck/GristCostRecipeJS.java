package com.havingfunrightnow.kubejsminestuck;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mraof.minestuck.alchemy.GristAmount;
import com.mraof.minestuck.alchemy.GristSet;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.util.ListJS;

public class GristCostRecipeJS extends RecipeJS {

    // stores grist costs
    public JsonElement grist_cost;

    @Override
    public void create(ListJS args) {
        inputItems.add(parseIngredientItem(args.get(0)));
        if (args.get(1) instanceof GristAmount grist) {
            grist_cost = new Gson().fromJson(getGristString(grist), JsonElement.class); //i love json ❤
        } else if (args.get(1) instanceof GristSet gristSet) {
            grist_cost = gristSet.serialize();
        } else {
            grist_cost = new Gson().toJsonTree(args.get(1)); // mapjs -> jsonelement
        }
    }

    public String getGristString(GristAmount grist) {
        return "{\""+grist.getType().toString()+"\":"+String.valueOf(grist.getAmount())+"}";
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