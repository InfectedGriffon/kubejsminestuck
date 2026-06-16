package xyz.atarax.kubejsminestuck;

import com.mraof.minestuck.alchemy.CardCaptchas;
import com.mraof.minestuck.block.MSBlocks;
import com.mraof.minestuck.item.CaptchaCardItem;
import com.mraof.minestuck.item.MSItems;
import com.mraof.minestuck.item.components.EncodedItemComponent;
import com.mraof.minestuck.player.IdentifierHandler;
import com.mraof.minestuck.player.PlayerIdentifier;
import com.mraof.minestuck.skaianet.SburbConnections;
import com.mraof.minestuck.util.ColorHandler;
import com.mraof.minestuck.util.MSDamageSources;
import com.mraof.minestuck.world.GateHandler;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;

@SuppressWarnings("unused")
public class Utils {
    @Info("Creates a captcha card with an itemstack stored inside")
    public static ItemStack createCardWithItem(ItemStack item, MinecraftServer server) {
        return CaptchaCardItem.createCardWithItem(item, server);
    }
    @Info("Creates a captcha card with the ghost of an itemstack stored inside")
    public static ItemStack createGhostCard(ItemStack item, MinecraftServer server) {
        return CaptchaCardItem.createGhostCard(item, server);
    }
    @Info("Creates a captcha card with the code of an item punched onto it")
    public static ItemStack createPunchedCard(Item item) {
        return EncodedItemComponent.createEncoded(MSItems.CAPTCHA_CARD, item);
    }
    @Info("Creates a cruxite dowel with the code of an item carved onto it")
    public static ItemStack createDowel(Item item) {
        return EncodedItemComponent.createEncoded(MSBlocks.CRUXITE_DOWEL, item);
    }
    @Info("Colors a text component based on a player's sburb textcolor")
    public static Component colorize(Component text, ServerPlayer player) {
        return text.copy().withColor(ColorHandler.getColorForPlayer(IdentifierHandler.encode(player), player.level()));
    }
    @Info("Finds an item from a captchalogue code")
    public static Item getItemFromCaptcha(String captcha, MinecraftServer server) {
        return CardCaptchas.getItemFromCaptcha(captcha, server);
    }
    @Info("Finds a captchalogue code from an item")
    public static String getCaptchaFromItem(Item item, MinecraftServer server) {
        return CardCaptchas.getCaptcha(item, server);
    }
    @Info("Teleports a player to one of their gates. Gates are 1, 2, 3 (maybe more... imagine...)")
    public static void teleportToGate(int gate, ServerPlayer player) {
        GateHandler.teleport(GateHandler.Type.values()[gate+1], player.serverLevel(), player);
    }
    @Info("Yeowch!")
    public static void decapitate(ServerPlayer player) {
        player.hurt(MSDamageSources.decapitation(player.level().registryAccess()), Float.MAX_VALUE);
    }
    @Info("Finds the server player of a given sburb client")
    public static ServerPlayer sburbServerOfClient(ServerPlayer clientPlayer) {
        var id = Objects.requireNonNull(IdentifierHandler.encode(clientPlayer));
        return sburbServerOfClient(id, clientPlayer.server);
    }
    @Info("Finds the primary client player of a given sburb server")
    public static ServerPlayer sburbClientOfServer(ServerPlayer serverPlayer) {
        var id = Objects.requireNonNull(IdentifierHandler.encode(serverPlayer));
        return sburbClientOfServer(id, serverPlayer.server);
    }
    @Info("Finds the server player of a given sburb client")
    public static ServerPlayer sburbServerOfClient(PlayerIdentifier clientId, MinecraftServer server) {
        return SburbConnections.get(server)
                .primaryPartnerForClient(clientId)
                .map(n -> n.getPlayer(server))
                .orElse(null);
    }
    @Info("Finds the primary client player of a given sburb server")
    public static ServerPlayer sburbClientOfServer(PlayerIdentifier serverId, MinecraftServer server) {
        return SburbConnections.get(server)
                .primaryPartnerForServer(serverId)
                .map(n -> n.getPlayer(server))
                .orElse(null);
    }
}
