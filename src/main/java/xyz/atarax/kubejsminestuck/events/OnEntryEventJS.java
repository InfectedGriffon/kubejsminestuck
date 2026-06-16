package xyz.atarax.kubejsminestuck.events;

import com.mraof.minestuck.event.OnEntryEvent;
import com.mraof.minestuck.player.PlayerIdentifier;
import dev.latvian.mods.kubejs.player.KubePlayerEvent;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

@SuppressWarnings("unused")
public class OnEntryEventJS implements KubePlayerEvent {
    final OnEntryEvent event;

    public OnEntryEventJS(OnEntryEvent event) {
        this.event = event;
    }

    @Info("The minecraft server the entry happened in")
    public MinecraftServer getMinecraftServer() {
        return event.getMcServer();
    }

    @Info("The player who entered")
    public PlayerIdentifier getPlayerId() {
        return event.getPlayer();
    }

    @Override
    public Player getEntity() {
        return event.getPlayer().getPlayer(event.getMcServer());
    }
}
