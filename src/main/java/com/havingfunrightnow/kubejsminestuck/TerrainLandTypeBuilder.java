package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.entity.MSEntityTypes;
import com.mraof.minestuck.entity.consort.ConsortEntity;
import com.mraof.minestuck.entity.consort.EnumConsort;
import com.mraof.minestuck.util.MSSoundEvents;
import com.mraof.minestuck.world.biome.LandBiomeType;
import com.mraof.minestuck.world.lands.terrain.TerrainLandType;
import dev.latvian.mods.kubejs.registry.BuilderBase;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.*;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class TerrainLandTypeBuilder extends BuilderBase<TerrainLandType> {
    private String[] names;
    private float skylight = 1.0f;
    private double fogColorR = 1.0, fogColorG = 1.0, fogColorB = 1.0;
    private double skyColorR = 1.0, skyColorG = 1.0, skyColorB = 1.0;
    private Supplier<SoundEvent> backgroundMusic = MSSoundEvents.MUSIC_DEFAULT;
    private Supplier<? extends EntityType<? extends ConsortEntity>> consortType = MSEntityTypes.SALAMANDER;

    private final Map<String, String> blockRegistry = new HashMap<>();
    private final List<CustomTerrainLandType.FeatureBuilder> featureBuilders = new ArrayList<>();

    public TerrainLandTypeBuilder(ResourceLocation id) {
        super(id);
    }

    @Override
    public TerrainLandType createObject() {
        TerrainLandType.Builder builder = new TerrainLandType.Builder(consortType);
        builder.names(names);
        builder.music(backgroundMusic);
        builder.skylight(skylight);
        builder.skyColor(skyColorR, skyColorG, skyColorB);
        builder.fogColor(fogColorR, fogColorG, fogColorB);
        return new CustomTerrainLandType(builder, blockRegistry, featureBuilders);
    }

    @Info("Sets the name(s) that the Land will have when referenced (such as using /checkland).")
    public TerrainLandTypeBuilder names(String[] names) {
        this.names = names;
        return this;
    }

    @Info("Determines which consort type spawns in the Land and what kind of buildings generate. Salamander by default.")
    public TerrainLandTypeBuilder consortType(String consort) {
        this.consortType = () -> EnumConsort.getFromName(consort).getConsortType();
        return this;
    }

    @Info("Adds to the StructureBlockRegistry. Without this, the Land will use the StructureBlockRegistry defaults and the ground will be stone.")
    public TerrainLandTypeBuilder addBlockRegistry(String entry, String block) {
        //uses a string for the block because non-Vanilla blocks have not been added to RegistryInfo.BLOCK at this stage
        this.blockRegistry.put(entry, block);
        return this;
    }

    @Info("Adds a PlacedFeature to the Land. Decoration and biome types are in all caps.")
    public TerrainLandTypeBuilder addFeature(String decorationStep, String featureName, String[] biomeTypes) {
        List<LandBiomeType> biomeTemp = new ArrayList<>();
        Arrays.stream(biomeTypes).toList().forEach(biome ->
                {
                    biomeTemp.add(LandBiomeType.valueOf(biome));
                }
        );

        LandBiomeType[] types = biomeTemp.toArray(new LandBiomeType[0]);
        this.featureBuilders.add(new CustomTerrainLandType.FeatureBuilder(GenerationStep.Decoration.valueOf(decorationStep), featureName, types));
        return this;
    }

    @Info("Determines skylight. Full brightness by default.")
    public TerrainLandTypeBuilder skylight(float skylight) {
        this.skylight = skylight;
        return this;
    }

    @Info("Sets the fog RGB color. The Title Land Type may also contribute to fog color, with the final product being a mix. Pure white by default.")
    public TerrainLandTypeBuilder fogColor(double red, double green, double blue) {
        this.fogColorR = red;
        this.fogColorG = green;
        this.fogColorB = blue;
        return this;
    }

    @Info("Sets the sky RGB color. Pure white by default.")
    public TerrainLandTypeBuilder skyColor(double red, double green, double blue) {
        this.skyColorR = red;
        this.skyColorG = green;
        this.skyColorB = blue;
        return this;
    }

    @Info("Sets the Land music. music.default by... default..")
    public TerrainLandTypeBuilder music(String musicName) {
        this.backgroundMusic = () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse(musicName));
        return this;
    }

    @Override
    public RegistryInfo<TerrainLandType> getRegistryType() {
        return KubeJSMinestuckPlugin.TERRAIN_LAND_TYPE;
    }
}