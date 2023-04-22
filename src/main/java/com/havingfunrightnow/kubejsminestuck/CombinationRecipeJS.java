package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.alchemy.CombinationMode;
import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class CombinationRecipeJS extends RecipeJS {

    Ingredient input1;
    Ingredient input2;
    ItemStack output;
    public CombinationMode mode;

    @Override
    public void create(RecipeArguments args) {
        output = parseItemOutput(args.get(0));
        input1 = parseItemInput(args.get(1));
        mode = CombinationMode.fromString(args.get(2).toString());
        input2 = parseItemInput(args.get(3));
    }

    @Override
    public void deserialize() {
        input1 = parseItemInput(json.get("input1"));
        input2 = parseItemInput(json.get("input2"));
        output = parseItemOutput(json.get("output"));
        mode = CombinationMode.fromString(json.get("mode").getAsString());
    }

    @Override
    public void serialize() {
        json.add("input1", input1.toJson());
        json.add("input2", input2.toJson());
        json.addProperty("mode", mode.asString());
        json.add("output", itemToJson(output));
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(input1) || match.contains(input2);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if(match.contains(input1))
            transformer.transform(this, match, input1, with);
        else if(match.contains(input2)) {
            transformer.transform(this, match, input2, with);
        }
        return true;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return match.contains(output);
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        transformer.transform(this, match, output, with);
        return true;
    }
}
