package com.havingfunrightnow.kubejsminestuck.event;

import com.havingfunrightnow.kubejsminestuck.KubeJSMinestuck;
import com.mraof.minestuck.event.*;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = KubeJSMinestuck.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE ,value = Dist.CLIENT)
public class MinestuckEvents {
    public static EventGroup GROUP = EventGroup.of("MinestuckEvents");
    public static EventHandler ALCHEMY = GROUP.server("alchemy", () -> AlchemyEventJS.class);
    public static EventHandler ON_ENTRY = GROUP.server("onEntry", () -> SburbEventJS.class);
    public static EventHandler CONNECTION_CLOSED = GROUP.server("connectionClosed", () -> SburbEventJS.class);
    public static EventHandler CONNECTION_CREATED = GROUP.server("connectionCreated", () -> SburbEventJS.class);
    public static EventHandler GRIST_DROPS = GROUP.server("gristDrops", () -> GristDropsEventJS.class);

    @SubscribeEvent
    static void onAlchemyEvent(AlchemyEvent event) {
        ALCHEMY.post(new AlchemyEventJS(event));
    }

    @SubscribeEvent
    static void onEntryEvent(SburbEvent.OnEntry event) {
        ON_ENTRY.post(new SburbEventJS(event));
    }

    @SubscribeEvent
    static void onConnectionClosedEvent(ConnectionClosedEvent event) {
        CONNECTION_CLOSED.post(new SburbEventJS(event));
    }

    @SubscribeEvent
    static void onConnectionCreatedEvent(ConnectionCreatedEvent event) {
        CONNECTION_CREATED.post(new SburbEventJS(event));
    }

    @SubscribeEvent
    static void onGristDropEvent(GristDropsEvent event) {
        GRIST_DROPS.post(new GristDropsEventJS(event));
    }
}
