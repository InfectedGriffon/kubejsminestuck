package com.havingfunrightnow.kubejsminestuck.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mraof.minestuck.alchemy.GristType;
import com.mraof.minestuck.command.argument.GristTypeArgument;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;

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
                            return summon(c.getSource(), 
                            BlockPosArgument.getSpawnablePos(c, "position"),
                            GristTypeArgument.getGristArgument(c, "type"),
                            LongArgumentType.getLong(c, "amount"));
                        })
                    )
                )
            )
        );
	}

    public static int summon(CommandSourceStack source, BlockPos pos, GristType type, Long value) {
        source.getServer().getCommands().performCommand(source, 
            "summon minestuck:grist "+pos.getX()+" "+pos.getY()+" "+pos.getZ()+" {Type:\""+type+"\",Value:"+value+"L}"
            //summon minestuck:grist ~ ~ ~ {Type:"minestuck:build",Value:100L}
        );
        return 1;
    }
}
