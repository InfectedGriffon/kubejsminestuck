package xyz.atarax.kubejsminestuck.events;

import com.mraof.minestuck.event.AlchemyEvent;
import com.mraof.minestuck.event.GristDropsEvent;
import com.mraof.minestuck.event.OnEntryEvent;
import com.mraof.minestuck.event.SburbEvent;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import xyz.atarax.kubejsminestuck.KubeJSMinestuck;

@EventBusSubscriber(modid = KubeJSMinestuck.MOD_ID)
public class MinestuckEventsJS {
    public static final EventGroup GROUP = EventGroup.of("MinestuckEvents");
    public static final EventHandler ALCHEMY = GROUP.server("alchemy", () -> AlchemyEventJS.class);
    public static final EventHandler ON_ENTRY = GROUP.server("onEntry", () -> OnEntryEventJS.class);
    public static final EventHandler CONNECTION_CLOSED = GROUP.server("connectionClosed", () -> ConnectionClosedJS.class);
    public static final EventHandler CONNECTION_CREATED = GROUP.server("connectionCreated", () -> ConnectionCreatedJS.class);
    public static final EventHandler GRIST_DROPS = GROUP.server("gristDrops", () -> GristDropsEventJS.class);

    @SuppressWarnings("unused")
    @SubscribeEvent
    static void onAlchemyEvent(AlchemyEvent event) {
        ALCHEMY.post(new AlchemyEventJS(event));
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    static void onEntryEvent(OnEntryEvent event) {
        ON_ENTRY.post(new OnEntryEventJS(event));
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    static void onConnectionClosedEvent(SburbEvent.ConnectionClosed event) {
        CONNECTION_CLOSED.post(new ConnectionClosedJS(event));
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    static void onConnectionCreatedEvent(SburbEvent.ConnectionCreated event) {
        CONNECTION_CREATED.post(new ConnectionCreatedJS(event));
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    static void onGristDropEvent(GristDropsEvent event) {
        GRIST_DROPS.post(new GristDropsEventJS(event));
    }
}
