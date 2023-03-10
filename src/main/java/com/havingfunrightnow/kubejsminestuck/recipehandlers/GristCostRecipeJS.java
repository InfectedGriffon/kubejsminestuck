package com.havingfunrightnow.kubejsminestuck.recipehandlers;

import com.google.gson.JsonObject;
import com.mraof.minestuck.Minestuck;

import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class GristCostRecipeJS extends RecipeJS {

    public Ingredient item;
    public JsonObject grist_cost = new JsonObject();

    @Override
    public void create(RecipeArguments args) {
        item = parseItemInput(args.get(0));
        if (args.size() == 1) {
            return; //free
        }
        assert args.size() % 2 != 0 : "Invalid Grist Cost Argument! Need pairs of names and amounts!";

        for (var i = 0; i < args.size(); i++) {
            var key = args.list().get(i).toString();
            grist_cost.addProperty(
                    key.contains(":") ? key : Minestuck.MOD_ID + ":" + key,
                    jsNumToLong( args.list().get(++i).toString())
            );
        }
    }

    @Override
    public void deserialize() {
        item = parseItemInput(json.get("ingredient"));
        grist_cost = json.get("grist_cost").getAsJsonObject();
    }

    @Override
    public void serialize() {
        json.add("ingredient", item.toJson());
        json.add("grist_cost", grist_cost);
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(item);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if (match.contains(item)) {
            item = transformer.transform(this, match, item, with);
            return true;
        }
        return false;
    }

    // no output for grist cost recipes
    @Override
    public boolean hasOutput(IngredientMatch match) {
        return false;
    }

    // no output for grist cost recipes
    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        return false;
    }

    private Long jsNumToLong(String s) {
        // scientific notation
        if (s.contains("E")) {
            return ((Double) Double.parseDouble(s)).longValue();
        }
        // every other number (will error on decimals)
        return Long.parseLong(s.replace(".0",""));
    }
}