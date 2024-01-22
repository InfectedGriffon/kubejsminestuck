package com.havingfunrightnow.kubejsminestuck.event;

import com.mraof.minestuck.event.SburbEvent;
import com.mraof.minestuck.skaianet.SburbConnection;
import com.mraof.minestuck.skaianet.Session;
import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.server.MinecraftServer;

@SuppressWarnings("unused")
public class SburbEventJS extends EventJS {
    final SburbEvent event;

    public SburbEventJS(SburbEvent event) {
        this.event = event;
    }

    public SburbConnection getConnection() {
        return this.event.getConnection();
    }

    public Session getSession() {
        return this.event.getSession();
    }

    public MinecraftServer getMinecraftServer() {
        return this.event.getMinecraftServer();
    }
}
