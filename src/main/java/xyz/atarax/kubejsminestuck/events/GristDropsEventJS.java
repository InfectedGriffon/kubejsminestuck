package xyz.atarax.kubejsminestuck.events;

import com.mraof.minestuck.api.alchemy.GristSet;
import com.mraof.minestuck.api.alchemy.GristType;
import com.mraof.minestuck.api.alchemy.MutableGristSet;
import com.mraof.minestuck.entity.underling.UnderlingEntity;
import com.mraof.minestuck.event.GristDropsEvent;
import dev.latvian.mods.kubejs.entity.KubeLivingEntityEvent;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class GristDropsEventJS implements KubeLivingEntityEvent {
    final GristDropsEvent event;

    public GristDropsEventJS(GristDropsEvent event) {
        this.event = event;
    }

    @Info("The underling entity that died")
    public UnderlingEntity getUnderling() {
        return this.event.getUnderling();
    }

    @Info("The type of underling that died")
    public EntityType<?> getUnderlingType() {
        return this.event.getUnderlingType();
    }

    @Info("How much damage each player did to the underling")
    public Map<Player, Double> getDamageMap() {
        return this.event.getDamageMap().entrySet().stream().collect(Collectors.toMap(
                e -> e.getKey().getPlayer(event.getUnderling().getServer()),
                Map.Entry::getValue
        ));
    }

    @Info("A set of the original grist drops")
    public GristSet getOriginalDrops() {
        return this.event.getOriginalDrops();
    }

    @Info("The underling's primary grist type")
    public GristType getPrimaryType() {
        return this.event.getPrimaryType();
    }

    @Info("The underling's secondary grist type")
    public GristType getBonusType() {
        return this.event.getBonusType();
    }

    @Info("A multiplier to grist amount supplied by the underling type")
    public double getOriginalMultiplier() {
        return this.event.getOriginalMultiplier();
    }

    @Info("A set of the drops after any modification")
    public MutableGristSet getNewDrops() {
        return this.event.getNewDrops();
    }

    @Info("Completely overrides the grist drops for this event.")
    public void setNewDrops(GristSet newDrops) {
        this.event.setNewDrops(newDrops);
    }

    @Override
    public LivingEntity getEntity() {
        return this.event.getUnderling();
    }
}
