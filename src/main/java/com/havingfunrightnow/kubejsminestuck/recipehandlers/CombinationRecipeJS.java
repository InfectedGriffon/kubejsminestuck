package com.havingfunrightnow.kubejsminestuck.recipehandlers;

import dev.latvian.mods.kubejs.recipe.*;
import com.mraof.minestuck.alchemy.CombinationMode;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class CombinationRecipeJS extends RecipeJS {

    public Ingredient input1, input2;
    public ItemStack output;
    public CombinationMode mode;

    @Override
    public void create(RecipeArguments args) {
        output = parseItemOutput(args.get(0));
		input1 = parseItemInput(args.get(1));
        mode = CombinationMode.fromString(args.getString(2 , null));
		input2 = parseItemInput(args.get(3));
    }

    @Override
    public void deserialize() {
        output = parseItemOutput(json.get("output"));
		input1 = parseItemInput(json.get("input1"));
        input2 = parseItemInput(json.get("input2"));
        mode = CombinationMode.fromString(json.get("mode").getAsString());
    }

    @Override
    public void serialize() {
        if (serializeInputs) {
            json.add("input1", input1.toJson());
            json.add("input2", input2.toJson());
            json.addProperty("mode", mode.asString());
        }
        if (serializeOutputs) {
            json.add("output", itemToJson(output));
        }
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(input1) || match.contains(input2);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if (match.contains(input1)) {
            input1 = transformer.transform(this, match, input1, with);
            return true;
        }

        if (match.contains(input2)) {
            input2 = transformer.transform(this, match, input2, with);
            return true;
        }

        return false;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return match.contains(output);
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        if (match.contains(output)) {
            output = transformer.transform(this, match, output, with);
            return true;
        }
        return false;
    }
}
