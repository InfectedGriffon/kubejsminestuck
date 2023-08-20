# kubejsminestuck

simple addon for [kubejs](https://www.curseforge.com/minecraft/mc-mods/kubejs) that adds support for [minestuck](https://www.curseforge.com/minecraft/mc-mods/minestuck)

## current features:
- Combination (totem lathe / punch designix)
    - `event.recipes.minestuck.combination(input1, input2, mode, output)`
- Grist Costs
    - `event.recipes.minestuck.grist_cost(item, {"grist_type": amount, ...}, priority?)`
      - the normal grist cost type
    - `event.recipes.minestuck.wildcard_grist_cost(item, amount, priority?)`
      - uses the specified amount of wildcard grist (usually build)
    - `event.recipes.minestuck.unavailable_grist_cost(item, priority?)`
      - makes an item unable to be alchemized if this recipe has priority
    - priority is optional and higher ones take precedence and default to 100
    - to make an item free, just put an empty object ({})
    - don't accidentally use minecraft grist by not typing the minestuck namespace
- Irradiating (cookalyzer)
    - same syntax as a normal furnace recipe
    - doesn't show up in jei
- Custom Grist
    - uses the startup event 'minestuck.grist'
    - `event.create('name').rarity(num).value(num)`
    - `.notUnderlingType()` and `.candy(item)` are optional
- Minestuck Playerdata
  - get and set various things related to minestuck, including:
    - color (get/set)
    - modus (get)
    - boondollars (get/set)
    - title (get/set once)
    - hero class/aspect (get)
    - echeladder rung + progress (get/set)
    - grist cache
      - use just `.grist` to get the whole cache
      - use `.getGrist(type)` to get the amount for a specific type
      - use `.addGrist(type, amount)` or `.addGrist({"grist_type": amount, ...})`