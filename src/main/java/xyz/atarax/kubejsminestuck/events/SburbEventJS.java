package xyz.atarax.kubejsminestuck.events;

import com.mraof.minestuck.event.SburbEvent;
import com.mraof.minestuck.skaianet.ActiveConnection;
import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.server.MinecraftServer;

@SuppressWarnings("unused")
public class SburbEventJS implements KubeEvent {
    final SburbEvent event;

    public SburbEventJS(SburbEvent event) {
        this.event = event;
    }

    @Info("The primary connection of the player this event happened to")
    public ActiveConnection getConnection() {
        return this.event.getConnection();
    }

    @Info("The minecraft server this event happened in")
    public MinecraftServer getMinecraftServer() {
        return this.event.getMinecraftServer();
    }
}
