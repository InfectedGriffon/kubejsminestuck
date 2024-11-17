package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.entity.consort.EnumConsort;
import com.mraof.minestuck.world.biome.LandBiomeType;
import com.mraof.minestuck.world.gen.structure.blocks.StructureBlockRegistry;
import com.mraof.minestuck.world.gen.structure.village.IguanaVillagePieces;
import com.mraof.minestuck.world.gen.structure.village.NakagatorVillagePieces;
import com.mraof.minestuck.world.gen.structure.village.SalamanderVillagePieces;
import com.mraof.minestuck.world.gen.structure.village.TurtleVillagePieces;
import com.mraof.minestuck.world.lands.LandBiomeGenBuilder;
import com.mraof.minestuck.world.lands.terrain.TerrainLandType;
import dev.latvian.mods.kubejs.registry.RegistryInfo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.List;
import java.util.Map;

public class CustomTerrainLandType extends TerrainLandType {
    private final Map<String, String> blockRegistry;
    private final List<FeatureBuilder> featureBuilders;

    protected CustomTerrainLandType(Builder builder, Map<String, String> blockRegistry, List<FeatureBuilder> featureBuilders) {
        super(builder);
        this.blockRegistry = blockRegistry;
        this.featureBuilders = featureBuilders;
    }

    @Override
    public void registerBlocks(StructureBlockRegistry structureBlockRegistry) {
        for (Map.Entry<String, String> entry : blockRegistry.entrySet())
            structureBlockRegistry.setBlock(entry.getKey(), RegistryInfo.BLOCK.getValue(ResourceLocation.tryParse(entry.getValue())));
    }

    @Override
    public void addBiomeGeneration(LandBiomeGenBuilder genBuilder, StructureBlockRegistry blocks) {
        super.addBiomeGeneration(genBuilder, blocks);

        for (FeatureBuilder featureBuilder : featureBuilders)
            featureBuilder.addFeature(genBuilder);
    }

    @Override
    public void addVillageCenters(CenterRegister register) {
        if (isConsortType(EnumConsort.SALAMANDER))
            SalamanderVillagePieces.addCenters(register);
        else if (isConsortType(EnumConsort.TURTLE))
            TurtleVillagePieces.addCenters(register);
        else if (isConsortType(EnumConsort.NAKAGATOR))
            NakagatorVillagePieces.addCenters(register);
        else if (isConsortType(EnumConsort.IGUANA))
            IguanaVillagePieces.addCenters(register);
    }

    @Override
    public void addVillagePieces(PieceRegister register, RandomSource random) {
        if (isConsortType(EnumConsort.SALAMANDER))
            SalamanderVillagePieces.addPieces(register, random);
        else if (isConsortType(EnumConsort.TURTLE))
            TurtleVillagePieces.addPieces(register, random);
        else if (isConsortType(EnumConsort.NAKAGATOR))
            NakagatorVillagePieces.addPieces(register, random);
        else if (isConsortType(EnumConsort.IGUANA))
            IguanaVillagePieces.addPieces(register, random);
    }

    public boolean isConsortType(EnumConsort enumConsort) {
        return enumConsort.getConsortType().equals(getConsortType());
    }

    public record FeatureBuilder(GenerationStep.Decoration step, String featureName, LandBiomeType... types) {
        public void addFeature(LandBiomeGenBuilder builder) {
            ResourceLocation featureLocation = ResourceLocation.tryParse(featureName);
            if (featureLocation != null)
                builder.addFeature(step, ResourceKey.create(Registries.PLACED_FEATURE, featureLocation), types);
        }
    }
}
