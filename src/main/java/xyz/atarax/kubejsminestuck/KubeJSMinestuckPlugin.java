package xyz.atarax.kubejsminestuck;

import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.api.alchemy.GristAmount;
import com.mraof.minestuck.api.alchemy.GristSet;
import com.mraof.minestuck.api.alchemy.GristTypes;
import com.mraof.minestuck.computer.editmode.DeployList;
import com.mraof.minestuck.world.lands.LandTypes;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.registry.BuilderTypeRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.latvian.mods.kubejs.util.AttachedData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import xyz.atarax.kubejsminestuck.events.MinestuckEventsJS;

public class KubeJSMinestuckPlugin implements KubeJSPlugin {
    @Override
    public void registerBuilderTypes(BuilderTypeRegistry registry) {
        registry.of(GristTypes.REGISTRY_KEY, callback -> callback.addDefault(GristBuilder.class, GristBuilder::new));
        registry.of(LandTypes.TERRAIN_KEY, callback -> callback.addDefault(TerrainLandTypeBuilder.class, TerrainLandTypeBuilder::new));
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(MinestuckEventsJS.GROUP);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("GristSet", GristSet.class);
        bindings.add("GristAmount", GristAmount.class);
        bindings.add("DeployList", DeployList.class);
        bindings.add("MinestuckUtils", Utils.class);
    }

    @Override
    public void attachPlayerData(AttachedData<Player> event) {
        event.add(Minestuck.MOD_ID, new KJSMPlayerData((ServerPlayer) event.getParent()));
    }
}
