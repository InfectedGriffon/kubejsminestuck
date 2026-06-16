package xyz.atarax.kubejsminestuck.events;

import com.mraof.minestuck.api.alchemy.GristSet;
import com.mraof.minestuck.event.AlchemyEvent;
import com.mraof.minestuck.player.PlayerIdentifier;
import dev.latvian.mods.kubejs.level.KubeLevelEvent;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("unused")
public class AlchemyEventJS implements KubeLevelEvent {
    final AlchemyEvent event;

    public AlchemyEventJS(AlchemyEvent event) {
        this.event = event;
    }

    @Info("The player who activated the alchemiter")
    public PlayerIdentifier getPlayer() {
        return this.event.getPlayer();
    }

    @Info("The alchemiter block entity used in this event")
    public BlockEntity getAlchemiter() {
        return this.event.getAlchemiter();
    }

    @Info("The level where this event took place")
    public Level getLevel() {
        return this.event.getLevel();
    }

    @Info("The cruxite dowel itemstack that is currently on/in the alchemiter")
    public ItemStack getDowel() {
        return this.event.getDowel();
    }

    @Info("The itemstack the alchemiter will spawn")
    public ItemStack getResult() {
        return this.event.getItemResult();
    }

    public void setResult(ItemStack result) {
        this.event.setItemResult(result);
    }

    @Info("A grist set containing the cost of this alchemization")
    public GristSet getCost() {
        return this.event.getCost();
    }
}
