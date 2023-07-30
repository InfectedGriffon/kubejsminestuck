# kubejsminestuck

simple addon for [kubejs](https://www.curseforge.com/minecraft/mc-mods/kubejs) that adds support for [minestuck](https://www.curseforge.com/minecraft/mc-mods/minestuck)

## current features:
- Combination (totem lathe / punch designix)
    - `event.recipes.minestuck.combination(input1, input2, mode, output)`
- Grist Costs
    - `event.recipes.minestuck.grist_cost(item, {"grist_type": amount, ...})`
    - to make an item free, just put an empty object ({})
    - don't accidentally use minecraft grist by not typing the minestuck namespace
- Irradiating (cookalyzer)
    - same syntax as a normal furnace recipe
    - doesn't show up in jei
- Custom Grist
    - uses the startup event 'minestuck.grist'
    - `event.create('name').rarity(num).value(num)`
    - `.notUnderlingType()` and `.candy(item)` are optional
