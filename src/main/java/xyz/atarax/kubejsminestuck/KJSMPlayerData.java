package xyz.atarax.kubejsminestuck;

import com.google.common.collect.ImmutableMap;
import com.mraof.minestuck.alchemy.GristHelper;
import com.mraof.minestuck.api.alchemy.*;
import com.mraof.minestuck.inventory.captchalogue.CaptchaDeckHandler;
import com.mraof.minestuck.player.*;
import com.mraof.minestuck.util.ColorHandler;
import net.minecraft.server.level.ServerPlayer;

import java.util.Objects;

public class KJSMPlayerData {
    private final PlayerData data;

    public KJSMPlayerData(ServerPlayer player) {
        this.data = PlayerData.get(player).orElseThrow();
    }

    public int getColor() {
        return -1; // TODO
    }
    public void setColor(int color) {
        ColorHandler.trySetPlayerColor(data.getPlayer(), color);
    }
    public String getModus() {
        return CaptchaDeckHandler.getModus(data.getPlayer()).getName().toString();
    }
    public long getBoondollars() {
        return PlayerBoondollars.getBoondollars(data);
    }
    public void setBoondollars(long amount) {
        PlayerBoondollars.setBoondollars(data, amount);
    }
    public void addBoondollars(long amount, boolean playSound) {
        PlayerBoondollars.addBoondollars(data, amount, playSound);
    }
    public void takeBoondollars(long amount) {
        PlayerBoondollars.takeBoondollars(data, amount, false);
    }
    public void takeBoondollars(long amount, boolean playSound) {
        PlayerBoondollars.takeBoondollars(data, amount, playSound);
    }
    public void tryTakeBoondollars(long amount) {
        PlayerBoondollars.tryTakeBoondollars(data, amount, false);
    }
    public void tryTakeBoondollars(long amount, boolean playSound) {
        PlayerBoondollars.tryTakeBoondollars(data, amount, playSound);
    }

    public String getTitle() {
        return Title.getTitle(data).map(Title::toString).orElse("??? of ???");
    }
    public String getHeroClass() {
        return Title.getTitle(data).map(t -> t.heroClass().toString()).orElse("???");
    }
    public String getHeroAspect() {
        return Title.getTitle(data).map(t -> t.heroAspect().toString()).orElse("???");
    }
    public void setTitle(String titleClass, String titleAspect) {
        var enumClass = Objects.requireNonNull(EnumClass.fromString(titleClass));
        var enumAspect = Objects.requireNonNull(EnumAspect.fromString(titleAspect));
        Title.setTitle(data, new Title(enumClass, enumAspect));
    }

    public int getRung() {
        return Echeladder.get(data).getRung();
    }
    public float getProgress() {
        return Echeladder.get(data).getProgress();
    }
    public void setRung(int rung) {
        Echeladder.get(data).setByCommand(rung, 0);
    }
    public void setRung(int rung, float progress) {
        Echeladder.get(data).setByCommand(rung, progress);
    }
    public void setRungProgress(float progress) {
        var echeladder = Echeladder.get(data);
        echeladder.setByCommand(echeladder.getRung(), progress);
    }
    public void increaseRungProgress(int progress) {
        Echeladder.get(data).increaseProgress(progress);
    }

    public ImmutableMap<GristType, Long> getGrist() {
        return (ImmutableMap<GristType, Long>) GristCache.get(data).getGristSet().asMap();
    }
    public long getGrist(GristType type) {
        return GristCache.get(data).getGristSet().getGrist(type);
    }
    public long addGrist(GristType type, long amount) {
        return GristCache.get(data).addWithinCapacity(new GristAmount(type, amount), GristHelper.EnumSource.CONSOLE).getGrist(type);
    }
    public MutableGristSet addGrist(GristSet gristSet) {
        return GristCache.get(data).addWithinCapacity(gristSet, GristHelper.EnumSource.CONSOLE);
    }
}