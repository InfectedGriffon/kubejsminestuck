// priority: 0

console.info('Hello, World! (You will only see this line once in console, during startup)')

onEvent('grist.registry', event => {
	event.create('blood', 0.3, 1.3).candyItem('minecraft:dirt').notUnderlingType()
})