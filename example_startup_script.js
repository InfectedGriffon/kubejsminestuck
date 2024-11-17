// priority: 0

console.info("Hello, World! (You will only see this line once in console, during startup)");

StartupEvents.registry("minestuck:grist", event => {
  event.create("lumber");
});

StartupEvents.registry("minestuck:terrain_land_type", event => {
  event.create("test_terrain")
  .names(["Terrain Test 1", "Terrain Test 2"])
  .consortType("salamander")
  .skylight(0.6)
  .fogColor(0.9, 0.2, 0.2)
  .skyColor(0.5, 0.9, 0.2)
  .music("minestuck:music.heat")
  .addFeature("VEGETAL_DECORATION", "minestuck:wooden_grass_patch", "NORMAL")
  .addFeature("VEGETAL_DECORATION", "minestuck:carved_bush_patch", ["NORMAL", "ROUGH"])
  .addBlockRegistry("ground", "minecraft:netherrack")
  .addBlockRegistry("surface", "minecraft:grass_block")
});