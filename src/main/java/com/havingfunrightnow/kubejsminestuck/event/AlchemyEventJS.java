package com.havingfunrightnow.kubejsminestuck.event;

import com.mraof.minestuck.api.alchemy.GristSet;
import com.mraof.minestuck.event.AlchemyEvent;
import com.mraof.minestuck.player.PlayerIdentifier;
import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("unused")
public class AlchemyEventJS extends EventJS {
    final AlchemyEvent event;

    public AlchemyEventJS(AlchemyEvent event) {
        this.event = event;
    }

    public PlayerIdentifier getPlayer() {
        return this.event.getPlayer();
    }

    public BlockEntity getAlchemiter() {
        return this.event.getAlchemiter();
    }

    public Level getLevel() {
        return this.event.getLevel();
    }

    public ItemStack getDowel() {
        return this.event.getDowel();
    }

    public ItemStack getResult() {
        return this.event.getItemResult();
    }

    public void setResult(ItemStack result) {
        this.event.setItemResult(result);
    }

    public GristSet getCost() {
        return this.event.getCost();
    }
}
