package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.api.alchemy.GristType;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class GristBuilder extends BuilderBase<GristType> {

    private float rarity = 0.3F;
    private float value = 2;
    private int color = 16777215;
    private float power = 0f;
    private boolean underlingType = false;
    private Supplier<ItemStack> candy = () -> ItemStack.EMPTY;

    public GristBuilder(ResourceLocation i) {
        super(i);
    }

    @Override
    public GristType createObject() {
        var properties = new GristType.Properties(rarity, value);
        if (this.underlingType)
            properties.underlingType(power, color);
        properties.candyStack(candy);
        return new GristType(properties);
    }

    @Info("Sets the rarity, which controls how often this type is used in generation.")
    public GristBuilder rarity(float rarity) {
        this.rarity = rarity;
        return this;
    }

    @Info("Sets the value, which is used in boondollar pricing and xp gain from alchemizing.")
    public GristBuilder value(float value) {
        this.value = value;
        return this;
    }

    @Info("""
            Makes this grist type available for underlings.
            
            Any underlings spawned with this type will have the specified color and power.
            """)
    public GristBuilder underlingType(int color, float power) {
        this.color = color;
        this.power = power;
        this.underlingType = true;
        return this;
    }

    @Info("""
            Sets the associated candy item.
            
            Candy items can be dropped by underlings instead of grist entities when killed by specific weapons.
            """)
    public GristBuilder candy(ItemStack candy) {
        this.candy = candy.isEmpty()? () -> ItemStack.EMPTY : () -> candy;
        return this;
    }

    @Override
    public RegistryInfo<GristType> getRegistryType() {
        return KubeJSMinestuckPlugin.GRIST;
    }
}
