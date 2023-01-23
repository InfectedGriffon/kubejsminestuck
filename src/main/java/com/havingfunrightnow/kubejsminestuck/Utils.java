package com.havingfunrightnow.kubejsminestuck;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import com.mraof.minestuck.Minestuck;
import com.mraof.minestuck.alchemy.GristAmount;
import com.mraof.minestuck.alchemy.GristSet;
import com.mraof.minestuck.alchemy.GristTypes;

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


    public static String fallbackNamespace(String str) {
        if (str.indexOf(':') >= 0) {
            return str;
        }

        return Minestuck.MOD_ID + ":" + str;
    }

    /**
     * @param list a list of alternating keys and values
     * @return a json element containing pairs from the list
     */
    public static JsonElement jsonifyPairs(List<Object> list) {

        if (list.size() % 2 != 0) {
            throw new IllegalArgumentException("Even number of keys/values needed!");
        }

        StringBuilder builder = new StringBuilder("{\"");

        for (var i = 0; i < list.size(); i+=2) {
            var key = fallbackNamespace(list.get(i).toString());
            var value = list.get(i+1).toString().replace(".0", "");
            builder.append(key+"\":"+value+",\"");
        }
        builder.delete(builder.length()-2, builder.length()); //get rid of last ," bit
        builder.append("}");
        LogUtils.getLogger().info("builder: {}", builder);
        return JsonParser.parseString(builder.toString());
    }
}