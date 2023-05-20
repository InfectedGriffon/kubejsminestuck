package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.alchemy.GristType;
import dev.latvian.mods.kubejs.BuilderBase;
import dev.latvian.mods.kubejs.RegistryObjectBuilderTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;
import java.util.function.Supplier;

public class GristBuilder extends BuilderBase<GristType> {

    private float rarity = 0.3F;
    private float value = 2;
    private boolean underlyingType = true;
    private Supplier<ItemStack> candy = () -> ItemStack.EMPTY;

    public GristBuilder(ResourceLocation i) {
        super(i);
    }

    @Override
    public GristType createObject() {
        var properties = new GristType.Properties(rarity, value);
        if (!underlyingType) {
            properties.notUnderlingType();
        }
        properties.candyStack(candy);
        return new GristType(properties);
    }

    public GristBuilder rarity(float rarity) {
        this.rarity = rarity;
        return this;
    }

    public GristBuilder value(float value) {
        this.value = value;
        return this;
    }

    public GristBuilder notUnderlingType() {
        this.underlyingType = false;
        return this;
    }

    public GristBuilder candy(ItemStack candy) {
        Objects.requireNonNull(candy);
        this.candy = candy.copy()::copy;
        return this;
    }

    @Override
    public RegistryObjectBuilderTypes<? super GristType> getRegistryType() {
        return KubeJSMinestuckPlugin.GRIST;
    }
}
