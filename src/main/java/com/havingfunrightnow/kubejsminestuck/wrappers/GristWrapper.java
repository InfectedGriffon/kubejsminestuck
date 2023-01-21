package com.havingfunrightnow.kubejsminestuck.wrappers;

import com.mraof.minestuck.alchemy.GristAmount;
import com.mraof.minestuck.alchemy.GristSet;
import com.mraof.minestuck.alchemy.GristType;
import com.mraof.minestuck.alchemy.GristTypes;

public class GristWrapper {
    public final static GristAmount free = new GristAmount(GristTypes.ZILLIUM, Long.MIN_VALUE);
    public static GristAmount of(GristType type, int amount) {
        return new GristAmount(type, amount);
    }
    public static GristSet set(GristAmount... grist) {
        return new GristSet(grist);
    }
}
