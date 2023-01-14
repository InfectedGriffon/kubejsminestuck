package com.havingfunrightnow.kubejsminestuck;

import com.mraof.minestuck.alchemy.GristAmount;
import com.mraof.minestuck.alchemy.GristType;

public interface GristWrapper {
    static GristAmount of(GristType type, int amount) {
        return new GristAmount(type, amount);
    }
}
