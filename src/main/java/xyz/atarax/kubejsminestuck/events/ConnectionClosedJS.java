package xyz.atarax.kubejsminestuck.events;

import com.mraof.minestuck.event.SburbEvent;
import com.mraof.minestuck.skaianet.ActiveConnection;
import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.server.MinecraftServer;

public class ConnectionClosedJS implements KubeEvent {
    final SburbEvent.ConnectionClosed event;

    public ConnectionClosedJS(SburbEvent.ConnectionClosed event) {
        this.event = event;
    }

    @Info("the primary connection of the player this event happened to")
    public ActiveConnection getConnection() {
        return this.event.getConnection();
    }

    @Info("the minecraft server this event happened in")
    public MinecraftServer getMinecraftServer() {
        return this.event.getMinecraftServer();
    }
}
