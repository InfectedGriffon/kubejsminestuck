package com.havingfunrightnow.kubejsminestuck;

import com.google.gson.JsonObject;
import com.mraof.minestuck.Minestuck;
import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class GristCostRecipeJS extends RecipeJS {

    public Ingredient item;
    public JsonObject gristCost = new JsonObject();

    @Override
    public void create(RecipeArguments args) {
        item = parseItemInput(args.get(0));
        if (args.size() == 1) {
            return; //free
        } else if (args.size() % 2 == 0) {
            throw new IllegalArgumentException("Invalid Grist Cost Argument! Need pairs of names and amounts!");
        }

        var iter = args.list().iterator();
        iter.next(); //skip over ingredient
        while (iter.hasNext()) {
            var key = iter.next().toString();                  // grist id
            gristCost.addProperty(
                    key.contains(":")? key: Minestuck.MOD_ID+":"+key, // add minestuck namespace
                    jsNumToLong(iter.next().toString())               // grist amount
            );
        }
    }

    /**
     * javascript for turns large numbers into scientific notation
     * @param s the string to parse
     * @return parsed number
     */
    private Long jsNumToLong(String s) {
        // scientific notation
        if (s.contains("E")) {
            return Double.valueOf(s).longValue();
        }
        // every other number (will error on decimals)
        return Long.parseLong(s.replace(".0",""));
    }

    @Override
    public void deserialize() {
        item = parseItemInput(json.get("ingredient"));
        gristCost = json.get("grist_cost").getAsJsonObject();
    }

    @Override
    public void serialize() {
        json.add("ingredient", item.toJson());
        json.add("grist_cost", gristCost);
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(item);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        transformer.transform(this, match, item, with);
        return true;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        return false;
    }
}
