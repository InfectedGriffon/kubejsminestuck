package com.havingfunrightnow.kubejsminestuck;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mraof.minestuck.alchemy.GristAmount;
import com.mraof.minestuck.alchemy.GristSet;
import com.mraof.minestuck.alchemy.GristType;
import com.mraof.minestuck.alchemy.GristTypes;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.resources.ResourceLocation;

public class Utils {
    /**
     * @param someObject some artbitrary object to conver into json
     * @return json element representing said object
     */
    public static JsonElement jsonify(Object someObject) {
        if (someObject instanceof JsonElement j) {return j;}

        if (someObject instanceof GristAmount grist) {
            if (grist.getType() == GristTypes.ZILLIUM.get() && grist.getAmount() == Long.MIN_VALUE) {
                return JsonParser.parseString("{}");
            }
            return jsonify(grist.getType(), grist.getAmount());
        }

        if (someObject instanceof GristSet gristSet) {
            return gristSet.serialize();
        }

        return new Gson().toJsonTree(someObject);
    }

    /**
     * @param key part before the colon
     * @param value part after the colon
     * @return json element formatted like {key:value}
     * @throws JsonParseException - if key or value contain json special characters
     * @throws JsonSyntaxException
     */
    public static JsonElement jsonify(Object key, Object value) {
        return JsonParser.parseString("{\"" + key.toString() + "\":" + value.toString() + "}");
    }

    /**
     * defaults usefallbacknamespace to true
     * @param theVeryObjectSelectedForThisMethodRightNow the object to turn into a grist type
     * @return the grist type parsed
     */
    public static GristType getGrist(Object theVeryObjectSelectedForThisMethodRightNow) {
        return getGrist(theVeryObjectSelectedForThisMethodRightNow, true);
    }

    /**
     * @param saidObjectInQuestion the object to turn into a grist type
     * @param useFallbackNamespace whether to attach minestuck: before when missing a namespace
     * @return the grist type parsed
     */
    public static GristType getGrist(Object saidObjectInQuestion, boolean useFallbackNamespace) {
        if (saidObjectInQuestion instanceof GristType grist) {return grist;} //return self when grist
        var stringified = saidObjectInQuestion.toString();
        if (!stringified.contains(":") && useFallbackNamespace) { //default to minestuck when no namespace
            stringified = "minestuck:" + stringified;
        }
        var type = GristTypes.getRegistry().getValue(new ResourceLocation(stringified));
        if(type==null) {
            ConsoleJS.SERVER.error("Invalid Grist Type: "+stringified+"!");
        }
        return type;
    }

    /**
     * @param someObject the object to inspect
     * @return the grist amount parsed
     */
    public static GristAmount getGristAmount(Object someObject) {
        if (someObject instanceof GristAmount grist) {return grist;}
        String[] s = someObject
            .toString()
            .replaceAll("\\{(.*)\\}","$1")
            .split(":");
        GristType type = getGrist(s[0]+":"+s[1], false);
        Long amount = Long.parseLong(s[2].replaceAll("\\s|\\..*",""));
        return new GristAmount(type, amount);
    }
}
