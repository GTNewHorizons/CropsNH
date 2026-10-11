package com.gtnewhorizon.cropsnh.crops.data;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.gtnewhorizon.cropsnh.api.ICropCard;

class MutationSpecTest {

    @Test
    void parentIdsGetTheCropsNHPrefix() {
        MutationSpec spec = new MutationSpec().parents("onion", "natura:berry");
        assertArrayEquals(new String[] { "cropsnh:onion", "natura:berry" }, spec.getParentIds());
    }

    @Test
    void needsTwoToFourParents() {
        assertThrows(IllegalArgumentException.class, () -> new MutationSpec().parents("onion"));
        assertThrows(IllegalArgumentException.class, () -> new MutationSpec().parents("a", "b", "c", "d", "e"));
    }

    @Test
    void resolvesRegisteredParentsInOrder() {
        Map<String, ICropCard> registry = new HashMap<>();
        DataCrop onion = new DataCrop("onion", 0, 0);
        DataCrop allium = new DataCrop("allium", 0, 0);
        registry.put(onion.getId(), onion);
        registry.put(allium.getId(), allium);
        MutationSpec spec = new MutationSpec().parents("onion", "allium");
        assertArrayEquals(new ICropCard[] { onion, allium }, spec.resolveParents(registry::get));
    }

    @Test
    void resolvesToNullWhenAParentIsMissing() {
        Map<String, ICropCard> registry = new HashMap<>();
        DataCrop wheat = new DataCrop("wheat", 0, 0);
        registry.put(wheat.getId(), wheat);
        MutationSpec spec = new MutationSpec().parents("wheat", "raspberry");
        assertNull(spec.resolveParents(registry::get));
    }
}
