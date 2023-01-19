package com.havingfunrightnow.kubejsminestuck;

import java.util.function.Supplier;
import com.mraof.minestuck.alchemy.GristType;
import dev.latvian.mods.kubejs.BuilderBase;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class KubeJSGristBuilder extends BuilderBase<GristType> {

    private GristType.Properties properties;

    public KubeJSGristBuilder(ResourceLocation i) {
        super(i);
    }

    @Override
    public GristType createObject() {
        if (properties == null) {
            ConsoleJS.STARTUP.error("You need to supply rarity for grist");
        }
        return new GristType(properties);
    }

    public KubeJSGristBuilder setup(float rarity, float value) {
        properties = new GristType.Properties(rarity, value);
        return this;
    }

    public KubeJSGristBuilder setup(float rarity) {
        properties = new GristType.Properties(rarity);
        return this;
    }

    public KubeJSGristBuilder notUnderlingType() {
        properties.notUnderlingType();
        return this;
    }

    public KubeJSGristBuilder candy(Supplier<Item> item) {
        if (properties != null) {
            properties.candy(item);
        }
        return this;
    }

    public KubeJSGristBuilder candyStack(Supplier<ItemStack> item) {
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
