# kubejsminestuck

simple addon for [kubejs](https://www.curseforge.com/minecraft/mc-mods/kubejs) that adds support for recipes and grist from [minestuck](https://www.curseforge.com/minecraft/mc-mods/minestuck)

Now in 1.19.2 !!!

<hr>

### Combination Recipes
- Done in the Totem Lathe or Punch Designix
- output from item and/or another item
- `event.recipes.minestuck.combination( output, input1, mode, input2 )`
- mode is either `and` or `or`
### Irradiating Recipes
- secret recipe type found in the cookalyzer
- same syntax as smelting recipes
### Grist Costs
- apply grist costs to any item
- `event.recipes.minestuck.grist_cost( item, grist... )`
- grists are written as the name (e.g. "minestuck:build") followed by a number
- just add another type/amount pair after the first to use multiple types
### Custom Grist
- found in the startup event "minestuck.grist.registry"
- `event.create( name ).rarity( x ).value( y )`
- additionally, you can use `.notUnderlingType()` and `.candy( item )`
