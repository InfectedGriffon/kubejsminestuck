package com.havingfunrightnow.kubejsminestuck.event;

import com.mraof.minestuck.api.alchemy.GristSet;
import com.mraof.minestuck.api.alchemy.GristType;
import com.mraof.minestuck.api.alchemy.MutableGristSet;
import com.mraof.minestuck.entity.underling.UnderlingEntity;
import com.mraof.minestuck.event.GristDropsEvent;
import com.mraof.minestuck.player.PlayerIdentifier;
import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.world.entity.EntityType;

import java.util.Map;

@SuppressWarnings("unused")
public class GristDropsEventJS extends EventJS {
    final GristDropsEvent event;

    public GristDropsEventJS(GristDropsEvent event) {
        this.event = event;
    }

    public UnderlingEntity getUnderling()
    {
        return this.event.getUnderling();
    }

    public EntityType<?> getUnderlingType()
    {
        return this.event.getUnderlingType();
    }

    public Map<PlayerIdentifier, Double> getDamageMap()
    {
        return this.event.getDamageMap();
    }

    public GristSet getOriginalDrops()
    {
        return this.event.getOriginalDrops();
    }

    public GristType getPrimaryType()
    {
        return this.event.getPrimaryType();
    }

    public GristType getBonusType()
    {
        return this.event.getBonusType();
    }

    public double getOriginalMultiplier()
    {
        return this.event.getOriginalMultiplier();
    }

    public MutableGristSet getNewDrops()
    {
        return this.event.getNewDrops();
    }

    public void setNewDrops(GristSet newDrops)
    {
        this.event.setNewDrops(newDrops);
    }
}
