package com.havingfunrightnow.kubejsminestuck.event;

import com.mraof.minestuck.api.alchemy.GristSet;
import com.mraof.minestuck.event.AlchemyEvent;
import com.mraof.minestuck.player.PlayerIdentifier;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("unused")
public class AlchemyEventJS extends EventJS {
    final AlchemyEvent event;

    public AlchemyEventJS(AlchemyEvent event) {
        this.event = event;
    }

    @Info("the player who activated the alchemiter")
    public PlayerIdentifier getPlayer() {
        return this.event.getPlayer();
    }

    @Info("the alchemiter block entity used in this event")
    public BlockEntity getAlchemiter() {
        return this.event.getAlchemiter();
    }

    @Info("the level where this event took place")
    public Level getLevel() {
        return this.event.getLevel();
    }

    @Info("the cruxite dowel itemstack that is currently on/in the alchemiter")
    public ItemStack getDowel() {
        return this.event.getDowel();
    }

    @Info("the itemstack the alchemiter will spawn")
    public ItemStack getResult() {
        return this.event.getItemResult();
    }

    public void setResult(ItemStack result) {
        this.event.setItemResult(result);
    }

    @Info("a grist set containing the cost of this alchemization")
    public GristSet getCost() {
        return this.event.getCost();
    }
}
