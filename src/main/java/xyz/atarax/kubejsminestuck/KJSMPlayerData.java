package xyz.atarax.kubejsminestuck;

import com.google.common.collect.ImmutableMap;
import com.mraof.minestuck.alchemy.GristHelper;
import com.mraof.minestuck.api.alchemy.*;
import com.mraof.minestuck.inventory.captchalogue.CaptchaDeckHandler;
import com.mraof.minestuck.player.*;
import com.mraof.minestuck.skaianet.SburbHandler;
import com.mraof.minestuck.skaianet.SburbPlayerData;
import com.mraof.minestuck.util.ColorHandler;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
public class    KJSMPlayerData {
    private final PlayerData data;
    private final SburbPlayerData hsData;
    private final ServerPlayer player;

    public KJSMPlayerData(ServerPlayer player) {
        Objects.requireNonNull(player);
        this.data = PlayerData.get(player).orElseThrow();
        this.hsData = SburbPlayerData.get(player);
        this.player = player;
    }

    @Info("The sburb textcolor of the player")
    public int getColor() {
        try {
            return ColorHandler.getColorForPlayer(IdentifierHandler.encode(player), player.level());
        } catch (NullPointerException e) {
            return -1;
        }
    }
    @Info("Tries to set a player's sburb textcolor. Fails if they are already connected to a client or server")
    public void trySetColor(int color) {
        ColorHandler.trySetPlayerColor(player, color);
    }
    @Info("The player's fetch modus")
    public String getModus() {
        return Optional.ofNullable(CaptchaDeckHandler.getModus(player))
                .map(m -> m.getName().toString())
                .orElse("??? Modus");
    }
    @Info("The amount of boondollars the player has")
    public long getBoondollars() {
        return PlayerBoondollars.getBoondollars(data);
    }
    @Info("Sets the player's boondollars")
    public void setBoondollars(long amount) {
        PlayerBoondollars.setBoondollars(data, amount);
    }
    @Info("Adds a non-negative amount of boondollars, with optional sound")
    public void addBoondollars(long amount, boolean playSound) {
        PlayerBoondollars.addBoondollars(data, amount, playSound);
    }
    @Info("Removes a non-negative amount of boondollars")
    public void takeBoondollars(long amount) {
        PlayerBoondollars.takeBoondollars(data, amount, false);
    }
    @Info("Removes a non-negative amount of boondollars, with optional sound")
    public void takeBoondollars(long amount, boolean playSound) {
        PlayerBoondollars.takeBoondollars(data, amount, playSound);
    }
    @Info("Attempts to remove a non-negative amount of boondollars. Returns whether the withdrawal was successful")
    public boolean tryTakeBoondollars(long amount) {
        return PlayerBoondollars.tryTakeBoondollars(data, amount, false);
    }
    @Info("Attempts to remove a non-negative amount of boondollars, with sound. Returns whether the withdrawal was successful")
    public boolean tryTakeBoondollars(long amount, boolean playSound) {
        return PlayerBoondollars.tryTakeBoondollars(data, amount, playSound);
    }

    @Info("The player's hero title. (Formatted as \"X of Y\")")
    public String getTitle() {
        return Title.getTitle(data).map(Title::toString).orElse("??? of ???");
    }
    @Info("The player's hero class")
    public String getHeroClass() {
        return Title.getTitle(data).map(t -> t.heroClass().toString()).orElse("???");
    }
    @Info("The player's hero aspect")
    public String getHeroAspect() {
        return Title.getTitle(data).map(t -> t.heroAspect().toString()).orElse("???");
    }
    @Info("Attempts to set the player's hero title and aspect. Returns false if the player already had a title")
    public boolean setTitle(String titleClass, String titleAspect) {
        var enumClass = Objects.requireNonNull(EnumClass.fromString(titleClass));
        var enumAspect = Objects.requireNonNull(EnumAspect.fromString(titleAspect));
        try {
            Title.setTitle(data, new Title(enumClass, enumAspect));
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }
    @Info("For advanced users only. Attempts to set the title using the class/aspect enums directly")
    public boolean setTitle(EnumClass enumClass, EnumAspect enumAspect) {
        try {
            Title.setTitle(data, new Title(enumClass, enumAspect));
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    @Info("The player's current echeladder rung. Starts at 0")
    public int getRung() {
        return Echeladder.get(data).getRung();
    }
    @Info("The player's progress on their current echeladder rung")
    public float getProgress() {
        return Echeladder.get(data).getProgress();
    }
    @Info("The amount of Experience needed to reach the player's next echeladder rung")
    public long getRungProgressReq() {
        return Rungs.getProgressReq(getRung());
    }
    @Info("The grist capacity of the player's current echeladder rung")
    public long getRungGristCapacity() {
        return Rungs.getGristCapacity(getRung());
    }
    @Info("Sets the player's echeladder rung. (Resetting progress)")
    public void setRung(int rung) {
        Echeladder.get(data).setByCommand(rung, 0);
    }
    @Info("Sets the player's echeladder rung and progress")
    public void setRung(int rung, float progress) {
        Echeladder.get(data).setByCommand(rung, progress);
    }
    @Info("Sets the player's progress on their current echeladder rung")
    public void setRungProgress(float progress) {
        var echeladder = Echeladder.get(data);
        echeladder.setByCommand(echeladder.getRung(), progress);
    }
    @Info("Increases a player's progress on their current echeladder rung, advancing if they pass the requirements")
    public void increaseRungProgress(int progress) {
        Echeladder.get(data).increaseProgress(progress);
    }

    @Info("An immutable map of the player's grist cache, grist_type->long")
    public ImmutableMap<GristType, Long> getGrist() {
        return (ImmutableMap<GristType, Long>) GristCache.get(data).getGristSet().asMap();
    }
    @Info("How much grist of this type the player has")
    public long getGrist(GristType type) {
        return GristCache.get(data).getGristSet().getGrist(type);
    }
    @Info("Adds a specific type and amount of grist to the player's cache")
    public long addGrist(GristType type, long amount) {
        return GristCache.get(data).addWithinCapacity(new GristAmount(type, amount), GristHelper.EnumSource.CONSOLE).getGrist(type);
    }
    @Info("Adds a set of grist to the player's cache")
    public MutableGristSet addGrist(GristSet gristSet) {
        return GristCache.get(data).addWithinCapacity(gristSet, GristHelper.EnumSource.CONSOLE);
    }
    @Info("Has this player entered the medium yet")
    public boolean hasEntered() {
        return hsData.hasEntered();
    }
    @Info("The dimension of the player's land in the medium")
    public ResourceKey<Level> getLand() {
        return hsData.getLandDimension();
    }
    @Info("Sets a player's land to a dimension. Does not work if they already have a land")
    public void setLand(ResourceKey<Level> dim) {
        hsData.setLand(dim);
    }
    @Info("The player's cruxite artifact as an itemstack")
    public ItemStack getArtifactItem() {
        return SburbHandler.getEntryItem(player.level(), hsData);
    }
    @Info("The sburb client player of this server, if one exists")
    public ServerPlayer getSburbClient() {
        return Utils.sburbClientOfServer(player);
    }
    @Info("The sburb server player of this client, if one exists")
    public ServerPlayer getSburbServer() {
        return Utils.sburbServerOfClient(player);
    }
}
