package com.havingfunrightnow.kubejsminestuck.gristManip;

import java.util.function.Supplier;

import com.havingfunrightnow.kubejsminestuck.KubeJSMinestuckPlugin;
import com.mraof.minestuck.alchemy.GristType;
import dev.latvian.mods.kubejs.BuilderBase;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GristBuilder extends BuilderBase<GristType> {

    private GristType.Properties properties;

    public GristBuilder(ResourceLocation i) {
        super(i);
    }

    @Override
    public GristType createObject() {
        if (properties == null) {
            ConsoleJS.STARTUP.error("You need to supply rarity for grist");
        }
        return new GristType(properties);
    }

    public GristBuilder setup(float rarity, float value) {
        properties = new GristType.Properties(rarity, value);
        return this;
    }

    public GristBuilder setup(float rarity) {
        properties = new GristType.Properties(rarity);
        return this;
    }

    public GristBuilder notUnderlingType() {
        properties.notUnderlingType();
        return this;
    }

    public GristBuilder candy(Supplier<Item> item) {
        if (properties != null) {
            properties.candy(item);
        }
        return this;
    }

    public GristBuilder candyStack(Supplier<ItemStack> item) {
        if (properties != null) {
            properties.candyStack(item);
        }
        return this;
    }

    @Override
    public RegistryObjectBuilderTypes<GristType> getRegistryType() {
        return KubeJSMinestuckPlugin.GRIST;
    }

}
