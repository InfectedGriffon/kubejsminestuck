package com.havingfunrightnow.kubejsminestuck.wrappers;

import com.mraof.minestuck.alchemy.GristAmount;
import com.mraof.minestuck.alchemy.GristSet;

public interface GristSetWrapper {
    static GristSet from(GristAmount... grist) {
        return new GristSet(grist);
    }
}
