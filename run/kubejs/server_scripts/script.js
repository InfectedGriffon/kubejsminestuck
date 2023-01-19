// priority: 0

settings.logAddedRecipes = true
settings.logRemovedRecipes = true
settings.logSkippedRecipes = false
settings.logErroringRecipes = true

console.info('Hello, World! (You will see this line every time server resources reload)')

onEvent('recipes', event => {
	//combination
	event.recipes.minestuck.combination("minecraft:coarse_dirt", "minecraft:dirt", "minecraft:gravel", "and")
	event.remove({type:"minestuck:combination",output:"minecraft:diamond_ore"})

	//grist cost
	event.recipes.minestuck.grist_cost("minecraft:magenta_stained_glass", {"minestuck:build":4,"minestuck:tar":1})
	event.remove({type:"minestuck:grist_cost",input:"minecraft:dirt"})
	event.recipes.minestuckGristCost("minecraft:dirt", Grist.of("build", 3))
	event.recipes.minestuck.grist_cost("minecraft:coarse_dirt", GristSet.from(Grist.of("build",2),Grist.of("amber",4)))

	//irradiating
	event.recipes.minestuck.irradiating("minecraft:emerald", "minecraft:diamond").xp(0.1) //recipe type not in jei
})
