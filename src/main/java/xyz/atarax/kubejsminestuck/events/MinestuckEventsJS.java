package xyz.atarax.kubejsminestuck.events;

import com.mraof.minestuck.event.AlchemyEvent;
import com.mraof.minestuck.event.GristDropsEvent;
import com.mraof.minestuck.event.OnEntryEvent;
import com.mraof.minestuck.event.SburbEvent;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.neoforged.bus.api.SubscribeEvent;

public class MinestuckEventsJS {
    public static EventGroup GROUP = EventGroup.of("MinestuckEvents");
    public static EventHandler ALCHEMY = GROUP.server("alchemy", () -> AlchemyEventJS.class);
    public static EventHandler ON_ENTRY = GROUP.server("onEntry", () -> OnEntryEventJS.class);
    public static EventHandler CONNECTION_CLOSED = GROUP.server("connectionClosed", () -> ConnectionClosedJS.class);
    public static EventHandler CONNECTION_CREATED = GROUP.server("connectionCreated", () -> ConnectionCreatedJS.class);
    public static EventHandler GRIST_DROPS = GROUP.server("gristDrops", () -> GristDropsEventJS.class);

    @SubscribeEvent
    static void onAlchemyEvent(AlchemyEvent event) {
        ALCHEMY.post(new AlchemyEventJS(event));
    }

    @SubscribeEvent
    static void onEntryEvent(OnEntryEvent event) {
        ON_ENTRY.post(new OnEntryEventJS(event));
    }

    @SubscribeEvent
    static void onConnectionClosedEvent(SburbEvent.ConnectionClosed event) {
        CONNECTION_CLOSED.post(new ConnectionClosedJS(event));
    }

    @SubscribeEvent
    static void onConnectionCreatedEvent(SburbEvent.ConnectionCreated event) {
        CONNECTION_CREATED.post(new ConnectionCreatedJS(event));
    }

    @SubscribeEvent
    static void onGristDropEvent(GristDropsEvent event) {
        GRIST_DROPS.post(new GristDropsEventJS(event));
    }
}
