// priority: 0

console.info('Hello, World! (You will see this line every time server resources reload)')

ServerEvents.recipes(event => {
	
	event.recipes.minestuck.combination("minecraft:dirt", "minecraft:diamond", "or", "minecraft:emerald")

	event.recipes.minestuck.grist_cost("minecraft:coarse_dirt", "zillium", 400, "amber", 59)

})
