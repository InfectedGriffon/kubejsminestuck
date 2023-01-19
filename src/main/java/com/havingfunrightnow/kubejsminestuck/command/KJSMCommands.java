package com.havingfunrightnow.kubejsminestuck.command;

import com.havingfunrightnow.kubejsminestuck.KubeJSMinestuck;
import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KubeJSMinestuck.MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class KJSMCommands {
    @SubscribeEvent
    public static void serverStarting(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        SummonGristCommand.register(dispatcher);
    }
}
