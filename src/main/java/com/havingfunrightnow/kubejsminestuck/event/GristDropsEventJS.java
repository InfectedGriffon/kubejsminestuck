package com.havingfunrightnow.kubejsminestuck.event;

import com.mraof.minestuck.api.alchemy.GristSet;
import com.mraof.minestuck.api.alchemy.GristType;
import com.mraof.minestuck.api.alchemy.MutableGristSet;
import com.mraof.minestuck.entity.underling.UnderlingEntity;
import com.mraof.minestuck.event.GristDropsEvent;
import com.mraof.minestuck.player.PlayerIdentifier;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.world.entity.EntityType;

import java.util.Map;

@SuppressWarnings("unused")
public class GristDropsEventJS extends EventJS {
    final GristDropsEvent event;

    public GristDropsEventJS(GristDropsEvent event) {
        this.event = event;
    }

    @Info("the underling entity that died")
    public UnderlingEntity getUnderling()
    {
        return this.event.getUnderling();
    }

    @Info("the type of underling that died")
    public EntityType<?> getUnderlingType()
    {
        return this.event.getUnderlingType();
    }

    @Info("a map of players to the amount of damage they did to the underling")
    public Map<PlayerIdentifier, Double> getDamageMap()
    {
        return this.event.getDamageMap();
    }

    @Info("a grist set of the drops before any modification")
    public GristSet getOriginalDrops()
    {
        return this.event.getOriginalDrops();
    }

    @Info("the primary grist type of the underling killed")
    public GristType getPrimaryType()
    {
        return this.event.getPrimaryType();
    }

    @Info("the secondary grist type of the underling killed")
    public GristType getBonusType()
    {
        return this.event.getBonusType();
    }

    @Info("a multiplier to grist amount supplied by the underling type")
    public double getOriginalMultiplier()
    {
        return this.event.getOriginalMultiplier();
    }

    @Info("a grist set of the drops after any modification")
    public MutableGristSet getNewDrops()
    {
        return this.event.getNewDrops();
    }

    public void setNewDrops(GristSet newDrops)
    {
        this.event.setNewDrops(newDrops);
    }
}
