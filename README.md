# kubejsminestuck
 
simple addon for [kubejs](https://www.curseforge.com/minecraft/mc-mods/kubejs) that adds support for [minestuck](https://www.curseforge.com/minecraft/mc-mods/minestuck)

## current features:
- recipe handlers
    - combination (totem lathe / punch designix)
        - `event.recipes.minestuck.combination(output, input1, mode, input2)`
    - grist costs (alchemiter)
        - `event.recipes.minestuck.grist_cost(item, grist...)`
        - when removing, make sure the item is selected as input
        - grist is just the name of a grist type followed by a number
    - irradiating (cookalyzer)
        - same syntax and methods as a normal furnace recipe
- Command to summon grist
    - summongrist <position> <type> <amount>
- Ability to add new types of grist
    - uses the startup event 'minestuck.grist.registry'
    - `event.create('name').rarity(num).value(num)`
    - `.notUnderlingType()` and `.candy(item)` are optional
