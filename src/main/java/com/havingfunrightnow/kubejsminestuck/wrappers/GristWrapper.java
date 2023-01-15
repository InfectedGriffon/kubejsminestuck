package com.havingfunrightnow.kubejsminestuck.wrappers;

import com.mraof.minestuck.alchemy.GristAmount;
import com.mraof.minestuck.alchemy.GristType;
import com.mraof.minestuck.alchemy.GristTypes;

public interface GristWrapper {
    final GristAmount free = new GristAmount(GristTypes.ZILLIUM, Long.MIN_VALUE);
    static GristAmount of(GristType type, int amount) {
        return new GristAmount(type, amount);
    }
}
