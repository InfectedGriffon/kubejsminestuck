// priority: 0

ServerEvents.recipes(event => {
  // make item free
  event.recipes.minestuck.grist_cost("minecraft:cobblestone", {});

  // using custom grist types
  event.recipes.minestuck.grist_cost("minestuck:carved_heavy_planks", {
    "minestuck:build": 4,
    "kubejs:lumber": 1000,
  });

  event.recipes.minestuck.combination(
    "minestuck:carved_planks",
    "stone_bricks",
    "and",
    "minestuck:carved_heavy_planks"
  );
});
