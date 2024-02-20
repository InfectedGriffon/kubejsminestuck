package com.havingfunrightnow.kubejsminestuck.event;

import com.mraof.minestuck.event.ConnectionClosedEvent;
import com.mraof.minestuck.event.ConnectionCreatedEvent;
import com.mraof.minestuck.event.SburbEvent;
import com.mraof.minestuck.skaianet.SburbConnection;
import com.mraof.minestuck.skaianet.Session;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.server.MinecraftServer;

@SuppressWarnings("unused")
public class SburbEventJS extends EventJS {
    final SburbEvent event;

    public SburbEventJS(SburbEvent event) {
        this.event = event;
    }

    @Info("the primary connection of the player this event happened to")
    public SburbConnection getConnection() {
        return this.event.getConnection();
    }

    @Info("the sburb session this event happened in")
    public Session getSession() {
        return this.event.getSession();
    }

    @Info("the minecraft server this event happened in")
    public MinecraftServer getMinecraftServer() {
        return this.event.getMinecraftServer();
    }

    @Info("the type of connection between the players this event happened to")
    public ConnectionCreatedEvent.ConnectionType getConnectionType() {
        if (this.event instanceof ConnectionCreatedEvent connectionEvent)
            return connectionEvent.getConnectionType();
        else if (this.event instanceof ConnectionClosedEvent connectionEvent)
            return connectionEvent.getConnectionType();
        else
            return null;
    }
}
