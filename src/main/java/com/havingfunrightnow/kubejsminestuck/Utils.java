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
     * @param o some artbitrary object to conver into json
     * @return json element representing said object
     */
    public static JsonElement jsonify(Object o) {
        if (o instanceof JsonElement j) {return j;}

        if (o instanceof GristAmount grist) {
            if (grist.getType() == GristTypes.ZILLIUM.get() && grist.getAmount() == Long.MIN_VALUE) {
                return JsonParser.parseString("{}");
            }
            return jsonify(grist.getType(), grist.getAmount());
        }

        if (o instanceof GristSet gristSet) {
            return gristSet.serialize();
        }

        return new Gson().toJsonTree(o);
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
     * @param o the object to turn into a grist type
     * @return the grist type parsed
     */
    public static GristType getGrist(Object o) {
        return getGrist(o, true);
    }

    /**
     * @param o the object to turn into a grist type
     * @param useFallbackNamespace whether to attach minestuck: before when missing a namespace
     * @return the grist type parsed
     */
    public static GristType getGrist(Object o, boolean useFallbackNamespace) {
        if (o instanceof GristType grist) {return grist;} //return self when grist
        var s = o.toString();
        if (!s.contains(":") && useFallbackNamespace) { //default to minestuck when no namespace
            s = "minestuck:" + s;
        }
        var type = GristTypes.getRegistry().getValue(new ResourceLocation(s));
        if(type==null) {
            ConsoleJS.SERVER.error("Invalid Grist Type: "+s+"!");
        }
        return type;
    }

}
