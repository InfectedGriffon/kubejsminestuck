# kubejsminestuck
 
simple addon for [kubejs](https://www.curseforge.com/minecraft/mc-mods/kubejs) that adds support for [minestuck](https://www.curseforge.com/minecraft/mc-mods/minestuck)

## current features:
- recipe handlers
    - combination (totem lathe / punch designix)
        - event.recipes.minestuck.combination(output, input1, input2, mode)
    - grist costs (alchemiter)
        - event.recipes.minestuck.grist_cost(item, grist)
        - when removing, make sure the item(s) are selected as input
    - irradiating (cookalyzer)
        - just use it like a normal furnace recipe
- Global binding for grist amounts
    - `Grist.of(type, amount)`
    - `GristSet.from(grist, grist, ...)`
- Ability to add new types of grist
    - by that i mean it's still unimplemented
