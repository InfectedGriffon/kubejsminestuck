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
import net.minecraftforge.registries.ForgeRegistries;
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
        var player = source.getPlayerOrException();
        var item = player.getItemInHand(InteractionHand.MAIN_HAND);
        var alch = new ItemStack(MSItems.CAPTCHA_CARD.get(), 1, new CompoundTag());
        switch (which) {
        case punched:
            alch.getOrCreateTag().putBoolean("punched", true);
        case card:
            alch.getOrCreateTag().putInt("contentSize", item.getCount());
            break;
        case totem:
            alch = new ItemStack(MSItems.CRUXITE_DOWEL.get(), 1, new CompoundTag());
            alch.getOrCreateTag().putInt("color", PlayerSavedData.getData(player).getColor());
        }
        if (!item.isEmpty()) {
            alch.getOrCreateTag().putString("contentID", ForgeRegistries.ITEMS.getKey(item.getItem()).toString());
        }
        player.setItemInHand(InteractionHand.MAIN_HAND, alch);
        return 1;
    }
}