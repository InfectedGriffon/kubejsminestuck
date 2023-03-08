package com.havingfunrightnow.kubejsminestuck.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mraof.minestuck.command.argument.GristTypeArgument;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;

public class SummonGristCommand {
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
	{
        dispatcher.register(
            Commands.literal("summongrist")
            .requires(source -> source.hasPermission(2))
            .then(Commands.argument("position", BlockPosArgument.blockPos())
            .then(Commands.argument("type", GristTypeArgument.gristType())
            .then(Commands.argument("amount", LongArgumentType.longArg(0, 1000000))
            .executes(c -> {
                var pos = BlockPosArgument.getSpawnablePos(c, "position");
                var type = GristTypeArgument.getGristArgument(c, "type");
                var amount = LongArgumentType.getLong(c, "amount");
                return c.getSource().getServer().getCommands().performCommand(
                    c.getSource(),
                    "summon minestuck:grist "
                    +pos.getX()+" "+pos.getY()+" "+pos.getZ()+
                    " {Type:\""+type+"\",Value:"+amount+"L}"
                );
            }))))
        );
    }
}
