# KubeJS Minestuck

A small addon for [kubejs](https://www.curseforge.com/minecraft/mc-mods/kubejs) that adds support for [minestuck](https://www.curseforge.com/minecraft/mc-mods/minestuck), including recipes, custom grist types, and Sburb player data.

## Current Features:
- Combination Recipes (totem lathe / punch designix)
    - `combination(input1, input2, mode, output)`
    - inputs and output are all item IDs
    - mode can be "and" or "or"
- Grist Cost Recipes
    - `grist_cost(item, {"grist_type": amount, ...}, priority?)`
      - the most common type of grist cost
    - `wildcard_grist_cost(item, amount, priority?)`
      - uses the specified amount of wildcard grist (usually build)
    - `unavailable_grist_cost(item, priority?)`
      - makes an item unable to be alchemized if this recipe has priority
    - priority: recipes with higher priority take precedence, optional and defaults to 100
    - to make an item free, put an empty object ({})
    - resource locations default to minecraft, so make sure to include `minestuck:` in the id
- Irradiating Recipes (cookalyzer)
    - basically just a normal furnace recipe
- Custom Grist
    - uses the startup event `minestuck.grist`
    - chain up to four methods to modify properties, all optional
    - `rarity (float)`: controls use in world generation
    - `value (float)`: controls strength in alchemy and boondollar costs
    - `underlingType (int color, float power)`: controls color and strength of underlings
      - the color is a hexadecimal code, written easily as `0xRRGGBB`
    - `candy (item id)`: associated item
- Minestuck Playerdata
  
  get and set various properties related to minestuck, including:
  - color: can be obtained as a field (get) or 
  - modus (get)
  - boondollars (get/set)
  - title (get/set)
  - hero class/aspect (get)
  - echeladder rung + progress (get/set)
  - grist cache
    - use `.grist` to get the whole cache as an object
    - use `.getGrist(type)` to get the amount of a specific type
    - use `.addGrist(type, amount)` or `.addGrist({"grist_type": amount, ...})` to add to the cache