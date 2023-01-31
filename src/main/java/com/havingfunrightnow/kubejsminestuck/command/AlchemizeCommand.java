package com.havingfunrightnow.kubejsminestuck.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mraof.minestuck.item.MSItems;
import com.mraof.minestuck.player.PlayerSavedData;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.server.command.EnumArgument;

enum Alchemizable {
    card,
    punched,
    totem
}

public class AlchemizeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("alchemize")
            .requires(source -> source.hasPermission(2))
            .then(
                Commands.argument("type", EnumArgument.enumArgument(Alchemizable.class))
                .executes(c -> transformItem(c.getSource(), c.getArgument("type", Alchemizable.class)))
            )
        );
    }

    private static int transformItem(CommandSourceStack source, Alchemizable which) throws CommandSyntaxException {    
        // get player and current item (for use later)
        var player = source.getPlayerOrException();
        var item = player.getItemInHand(InteractionHand.MAIN_HAND);
        
        // what player's hand gets set to
        ItemStack alchemized = new ItemStack(MSItems.CAPTCHA_CARD.get(), 1, new CompoundTag());

        switch (which) {
            case card:
                alchemized.getOrCreateTag().putBoolean("punched", false);
                alchemized.getOrCreateTag().putInt("contentSize", item.getCount());
                break;
            case punched:
                alchemized.getOrCreateTag().putBoolean("punched", true);
                break;
            case totem:
                alchemized = new ItemStack(MSItems.CRUXITE_DOWEL.get(), 1, new CompoundTag());
                alchemized.getOrCreateTag().putInt("color", PlayerSavedData.getData(player).getColor());
                break;
        }
        
        // empty items need no contentID
        if (item != ItemStack.EMPTY) {
            alchemized.getOrCreateTag().putString("contentID", item.getItem().getCreatorModId(item)+":"+item.getItem().toString());
        }

        // finally, set item in hand to new alchemized item
        player.setItemInHand(InteractionHand.MAIN_HAND, alchemized);
        return 1;
    }
}