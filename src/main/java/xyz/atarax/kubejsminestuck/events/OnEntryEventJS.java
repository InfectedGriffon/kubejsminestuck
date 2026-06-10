package xyz.atarax.kubejsminestuck.events;

import com.mraof.minestuck.event.OnEntryEvent;
import com.mraof.minestuck.player.PlayerIdentifier;
import dev.latvian.mods.kubejs.event.KubeEvent;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.server.MinecraftServer;

@SuppressWarnings("unused")
public class OnEntryEventJS implements KubeEvent {
    final OnEntryEvent event;

    public OnEntryEventJS(OnEntryEvent event) {
        this.event = event;
    }

    @Info("The minecraft server the entry happened in")
    public MinecraftServer getMinecraftServer() {
        return this.event.getMcServer();
    }

    @Info("The player who entered")
    public PlayerIdentifier getPlayer() {
        return this.event.getPlayer();
    }
}
