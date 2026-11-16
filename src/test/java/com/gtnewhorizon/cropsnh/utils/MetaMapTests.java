package com.gtnewhorizon.cropsnh.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.minecraftforge.oredict.OreDictionary;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.gtnewhorizon.cropsnh.reference.Reference;
import com.gtnewhorizon.cropsnh.utility.MetaMap;

public class MetaMapTests {

    private static class TestMap<V> extends MetaMap<Object, V> {
    }

    private static final int ALTERNATE_WILDCARD = -1;
    private static final Object ITEM_1 = new Object();
    private static final Object ITEM_2 = new Object();
    private static final Object ITEM_3 = new Object();
    private static final Object ITEM_4 = new Object();

    @BeforeAll
    public static void beforeAll() {
        Reference.IS_GAME_LOADED = false;
    }

    // region null keys

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void putWildcard_CanInsertUseNullKey(boolean clearNonWildcard) {
        assertDoesNotThrow(() -> new TestMap<Boolean>().putWildcard(null, true, clearNonWildcard));
    }

    @ParameterizedTest
    @ValueSource(ints = { ALTERNATE_WILDCARD, 0, 1, OreDictionary.WILDCARD_VALUE })
    public void put_CanInsertUseNullKey(int meta) {
        assertDoesNotThrow(() -> new TestMap<Boolean>().put(null, meta, true));
    }

    @ParameterizedTest
    @ValueSource(ints = { ALTERNATE_WILDCARD, 0, 1, OreDictionary.WILDCARD_VALUE })
    public void putIfAbsent_CanInsertUseNullKey(int meta) {
        assertDoesNotThrow(() -> new TestMap<Boolean>().putIfAbsent(null, meta, true, true));
        assertDoesNotThrow(() -> new TestMap<Boolean>().putIfAbsent(null, meta, true, false));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5 })
    public void get_CanGetValueOfNullWildcard(int wildcardInsertionType) {
        final TestMap<Boolean> map = new TestMap<>();
        // insert default value
        map.put(null, 0, false);
        // insert wildcard
        switch (wildcardInsertionType) {
            case 0 -> map.putWildcard(null, true, false);
            case 1 -> map.putWildcard(null, true, true);
            case 2 -> map.put(null, ALTERNATE_WILDCARD, true);
            case 3 -> map.put(null, OreDictionary.WILDCARD_VALUE, true);
            case 4 -> map.putIfAbsent(null, ALTERNATE_WILDCARD, true, false);
            default -> map.putIfAbsent(null, OreDictionary.WILDCARD_VALUE, true, false);
        }
        // defined value should still return correct value
        final Boolean expectedKeyValue = wildcardInsertionType == 1;
        assertEquals(expectedKeyValue, map.get(null, 0), "wildcard insertion type: " + wildcardInsertionType);
        // any other value should return the wildcard
        assertEquals(Boolean.TRUE, map.get(null, 1), "wildcard insertion type: " + wildcardInsertionType);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5 })
    public void getOrDefault_CanGetValueOfNullWildcard(int wildcardInsertionType) {
        final TestMap<Boolean> map = new TestMap<>();
        // insert default value
        map.put(null, 0, false);
        // insert wildcard
        switch (wildcardInsertionType) {
            case 0 -> map.putWildcard(null, true, false);
            case 1 -> map.putWildcard(null, true, true);
            case 2 -> map.put(null, ALTERNATE_WILDCARD, true);
            case 3 -> map.put(null, OreDictionary.WILDCARD_VALUE, true);
            case 4 -> map.putIfAbsent(null, ALTERNATE_WILDCARD, true, false);
            default -> map.putIfAbsent(null, OreDictionary.WILDCARD_VALUE, true, false);
        }
        // defined value should still return correct value
        final Boolean expectedKeyValue = wildcardInsertionType == 1;
        assertEquals(
            expectedKeyValue,
            map.getOrDefault(null, 0, null),
            "wildcard insertion type: " + wildcardInsertionType);
        // any other value should return the wildcard
        assertEquals(
            Boolean.TRUE,
            map.getOrDefault(null, 1, null),
            "wildcard insertion type: " + wildcardInsertionType);
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5 })
    public void isEmpty_nullWildcardKeyIsNotEmpty(int wildcardInsertionType) {
        final TestMap<Boolean> map = new TestMap<>();
        assertTrue(map.isEmpty());
        // insert wildcard
        switch (wildcardInsertionType) {
            case 0 -> map.putWildcard(null, true, false);
            case 1 -> map.putWildcard(null, true, true);
            case 2 -> map.put(null, ALTERNATE_WILDCARD, true);
            case 3 -> map.put(null, OreDictionary.WILDCARD_VALUE, true);
            case 4 -> map.putIfAbsent(null, ALTERNATE_WILDCARD, true, false);
            default -> map.putIfAbsent(null, OreDictionary.WILDCARD_VALUE, true, false);
        }
        assertFalse(map.isEmpty());
    }

    // endregion null keys

    // region put

    @Test
    public void put_KeyMetaHasCorrectValue() {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedValue1 = 32;
        final int expectedValue2 = 22;
        final int expectedValue3 = 79;
        final int expectedValue4 = 46;
        map.put(ITEM_1, 0, expectedValue1);
        map.put(ITEM_1, 1, expectedValue2);
        map.put(ITEM_2, 0, expectedValue3);
        map.put(ITEM_2, 1, expectedValue4);
        // checks item+meta resolving
        assertEquals(expectedValue1, map.get(ITEM_1, 0));
        assertEquals(expectedValue2, map.get(ITEM_1, 1));
        assertEquals(expectedValue3, map.get(ITEM_2, 0));
        assertEquals(expectedValue4, map.get(ITEM_2, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = { ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void put_WildcardHasCorrectValue(int wildcardMeta) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedValue1 = 32;
        final int expectedValue2 = 22;
        map.put(ITEM_1, wildcardMeta, expectedValue1);
        map.put(ITEM_2, wildcardMeta, expectedValue2);
        assertEquals(expectedValue1, map.get(ITEM_1, 1));
        assertEquals(expectedValue2, map.get(ITEM_2, 0));
    }

    @Test
    public void put_KeyMetaCanOverrideExistingKeyMeta() {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedOriginal = 32;
        final int expectedReplacement = 22;
        map.put(ITEM_1, 0, expectedOriginal);
        assertEquals(expectedOriginal, map.get(ITEM_1, 0));
        map.put(ITEM_1, 0, expectedReplacement);
        assertEquals(expectedReplacement, map.get(ITEM_1, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = { ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void put_WildcardCanOverwriteExistingWildcards(int wildcardMeta) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedKeyMeta = 32;
        final int expectedWildcard = 22;
        map.put(ITEM_1, 0, expectedKeyMeta);
        map.put(ITEM_1, wildcardMeta, expectedWildcard);
        assertEquals(expectedKeyMeta, map.get(ITEM_1, 0));
        assertEquals(expectedWildcard, map.get(ITEM_1, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = { ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void put_KeyMetaCanBeInsertedEvenWhenWildcardsExist(int wildcardMeta) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedKeyMeta = 32;
        final int expectedWildcard = 22;
        map.put(ITEM_1, wildcardMeta, expectedWildcard);
        map.put(ITEM_1, 0, expectedKeyMeta);
        assertEquals(expectedKeyMeta, map.get(ITEM_1, 0));
        // meta 1 is never inserted so it should be the wildcard
        assertEquals(expectedWildcard, map.get(ITEM_1, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = { ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void put_WildcardDoNotOverrideExistingSetValues(int wildcardMeta) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedValue1 = 32;
        final int expectedValue2 = 22;
        map.put(ITEM_1, 0, expectedValue1);
        map.put(ITEM_1, wildcardMeta, expectedValue2);
        assertEquals(expectedValue1, map.get(ITEM_1, 0));
        // meta 1 is never set, so it should return the wildcard value
        assertEquals(expectedValue2, map.get(ITEM_1, 1));
    }

    // endregion put

    // region putWildcard

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void putWildcard_HasCorrectValue(boolean clearNonWildcards) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedValue1 = 32;
        final int expectedValue2 = 22;
        map.putWildcard(ITEM_1, expectedValue1, clearNonWildcards);
        map.putWildcard(ITEM_2, expectedValue2, clearNonWildcards);
        // checks item+meta resolving
        assertEquals(expectedValue1, map.get(ITEM_1, 0));
        assertEquals(expectedValue1, map.get(ITEM_1, 1));
        assertEquals(expectedValue2, map.get(ITEM_2, 0));
        assertEquals(expectedValue2, map.get(ITEM_2, 1));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void putWildcard_OverridesExistingWildcard(boolean clearNonWildcards) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedValue1 = 32;
        final int expectedValue2 = 22;
        // set wildcard
        map.putWildcard(ITEM_1, expectedValue1, clearNonWildcards);
        // should be meta agnostic
        assertEquals(expectedValue1, map.get(ITEM_1, 0));
        assertEquals(expectedValue1, map.get(ITEM_1, 1));
        // override existing value
        map.putWildcard(ITEM_1, expectedValue2, clearNonWildcards);
        // should be meta agnostic
        assertEquals(expectedValue2, map.get(ITEM_1, 0));
        assertEquals(expectedValue2, map.get(ITEM_1, 1));
    }

    @Test
    public void putWildcard_ClearNonWildcardsClearsKeyMetasWhenTrue() {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedKeyMeta1 = 32;
        final int expectedKeyMeta2 = 78;
        final int expectedKeyMeta3 = 94;
        final int expectedWildcard = 22;
        // insert key-meta entry
        map.put(ITEM_1, 0, expectedKeyMeta1);
        map.put(ITEM_1, 1, expectedKeyMeta2);
        map.put(ITEM_2, 0, expectedKeyMeta3);
        // ensure item_1 values have are correct
        assertEquals(expectedKeyMeta1, map.get(ITEM_1, 0));
        assertEquals(expectedKeyMeta2, map.get(ITEM_1, 1));
        map.putWildcard(ITEM_1, expectedWildcard, true);
        // old item_1 values should now be unset.
        assertEquals(expectedWildcard, map.get(ITEM_1, 0));
        assertEquals(expectedWildcard, map.get(ITEM_1, 1));
        // previously unset value should return wildcard
        assertEquals(expectedWildcard, map.get(ITEM_1, 2));
        // item_2 keys should be untouched
        assertEquals(expectedKeyMeta3, map.get(ITEM_2, 0));
    }

    @Test
    public void putWildcard_ClearNonWildcardsDoesNotClearKeyMetasWhenFalse() {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedKeyMeta1 = 32;
        final int expectedKeyMeta2 = 78;
        final int expectedKeyMeta3 = 94;
        final int expectedWildcard = 22;
        // insert key-meta entry
        map.put(ITEM_1, 0, expectedKeyMeta1);
        map.put(ITEM_1, 1, expectedKeyMeta2);
        map.put(ITEM_2, 0, expectedKeyMeta3);
        map.putWildcard(ITEM_1, expectedWildcard, false);
        // unset key returns wildcard
        assertEquals(expectedWildcard, map.get(ITEM_1, 2));
        // original keys are untouched
        assertEquals(expectedKeyMeta1, map.get(ITEM_1, 0));
        assertEquals(expectedKeyMeta2, map.get(ITEM_1, 1));
        assertEquals(expectedKeyMeta3, map.get(ITEM_2, 0));
    }

    // endregion putWildcard

    // region putIfAbsent

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void putIfAbsent_KeyMetaHasCorrectValue(boolean ignoreExistingWildcard) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedValue1 = 32;
        final int expectedValue2 = 22;
        final int expectedValue3 = 79;
        final int expectedValue4 = 46;
        assertTrue(map.putIfAbsent(ITEM_1, 0, expectedValue1, ignoreExistingWildcard));
        assertTrue(map.putIfAbsent(ITEM_1, 1, expectedValue2, ignoreExistingWildcard));
        assertTrue(map.putIfAbsent(ITEM_2, 0, expectedValue3, ignoreExistingWildcard));
        assertTrue(map.putIfAbsent(ITEM_2, 1, expectedValue4, ignoreExistingWildcard));
        // checks item+meta resolving
        assertEquals(expectedValue1, map.get(ITEM_1, 0));
        assertEquals(expectedValue2, map.get(ITEM_1, 1));
        assertEquals(expectedValue3, map.get(ITEM_2, 0));
        assertEquals(expectedValue4, map.get(ITEM_2, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    public void putIfAbsent_WildcardHasCorrectValue(int type) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedValue1 = 32;
        final int expectedValue2 = 22;
        int wildcardMeta;
        boolean ignoreExistingWildcard;
        switch (type) {
            case 0 -> {
                ignoreExistingWildcard = false;
                wildcardMeta = OreDictionary.WILDCARD_VALUE;
            }
            case 1 -> {
                ignoreExistingWildcard = true;
                wildcardMeta = OreDictionary.WILDCARD_VALUE;
            }
            case 2 -> {
                ignoreExistingWildcard = false;
                wildcardMeta = ALTERNATE_WILDCARD;
            }
            default -> {
                ignoreExistingWildcard = true;
                wildcardMeta = ALTERNATE_WILDCARD;
            }
        }
        assertTrue(map.putIfAbsent(ITEM_1, wildcardMeta, expectedValue1, ignoreExistingWildcard));
        assertTrue(map.putIfAbsent(ITEM_2, wildcardMeta, expectedValue2, ignoreExistingWildcard));
        assertEquals(expectedValue1, map.get(ITEM_1, 1));
        assertEquals(expectedValue2, map.get(ITEM_2, 0));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void putIfAbsent_KeyMetaCantOverrideExistingKeyMeta(boolean ignoreExistingWildcards) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedOriginal = 32;
        final int expectedReplacement = 22;
        // first key should insert just fine
        assertTrue(map.putIfAbsent(ITEM_1, 0, expectedOriginal, ignoreExistingWildcards));
        assertEquals(expectedOriginal, map.get(ITEM_1, 0));
        // should fail to overwrite
        assertFalse(map.putIfAbsent(ITEM_1, 0, expectedReplacement, ignoreExistingWildcards));
        // value shouldn't have changed
        assertEquals(expectedOriginal, map.get(ITEM_1, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    public void putIfAbsent_WildcardCantOverrideExistingWildcard(int type) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedOriginal = 32;
        final int expectedReplacement = 22;
        int wildcardMeta = type % 2 == 0 ? ALTERNATE_WILDCARD : OreDictionary.WILDCARD_VALUE;
        boolean ignoreExistingWildcards = type < 2;
        // first key should insert just fine
        assertTrue(map.putIfAbsent(ITEM_1, wildcardMeta, expectedOriginal, ignoreExistingWildcards));
        // should fail to overwrite
        assertFalse(map.putIfAbsent(ITEM_1, wildcardMeta, expectedReplacement, ignoreExistingWildcards));
        // value shouldn't have changed
        assertEquals(expectedOriginal, map.get(ITEM_1, 0));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void putIfAbsent_KeyMetaCanInsertIfWildcardExistsAndIgnoreExistingWildcardIsTrue(boolean clearNonWildcards) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedKeyMeta = 32;
        final int expectedWildcard = 22;
        // insert wildcard
        map.putWildcard(ITEM_1, expectedWildcard, clearNonWildcards);
        // wildcard should ignore meta
        assertEquals(expectedWildcard, map.get(ITEM_1, 0));
        assertEquals(expectedWildcard, map.get(ITEM_1, 1));
        // insert key meta value and ignore existing wildcards
        assertTrue(map.putIfAbsent(ITEM_1, 0, expectedKeyMeta, true));
        // ensure that the key meta has the right value
        assertEquals(expectedKeyMeta, map.get(ITEM_1, 0));
        // ensure that the wildcard still applies
        assertEquals(expectedWildcard, map.get(ITEM_1, 1));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void putIfAbsent_KeyMetaCantInsertIfWildcardExistsAndIgnoreExistingWildcardIsFalse(
        boolean clearNonWildcards) {
        final TestMap<Integer> map = new TestMap<>();
        final int expectedKeyMeta = 32;
        final int expectedWildcard = 22;
        // insert wildcard
        map.putWildcard(ITEM_1, expectedWildcard, clearNonWildcards);
        // wildcard should ignore meta
        assertEquals(expectedWildcard, map.get(ITEM_1, 0));
        assertEquals(expectedWildcard, map.get(ITEM_1, 1));
        // insert key meta value and ignore existing wildcards
        assertFalse(map.putIfAbsent(ITEM_1, 0, expectedKeyMeta, false));
        // ensure that the key meta has the right value
        assertEquals(expectedWildcard, map.get(ITEM_1, 0));
        // ensure that the wildcard still applies
        assertEquals(expectedWildcard, map.get(ITEM_1, 1));
    }

    // endregion putIfAbsent

    // region get

    @ParameterizedTest
    @ValueSource(ints = { 0, ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void get_ReturnsNullWhenNotFound(int meta) {
        final TestMap<Boolean> map = new TestMap<>();
        assertNull(map.get(ITEM_1, meta));
    }

    @Test
    public void get_ReturnsCorrectValue() {
        final TestMap<Integer> map = new TestMap<>();
        final int expected1Wildcard = 0;
        final int expected1KeyMeta = 1;
        final int expected20 = 2;
        final int expected21 = 2;
        map.putWildcard(ITEM_1, expected1Wildcard, false);
        map.put(ITEM_1, 0, expected1KeyMeta);
        map.put(ITEM_2, 0, expected20);
        map.put(ITEM_2, 1, expected21);
        // check item 1 wildcard responses
        assertEquals(expected1Wildcard, map.get(ITEM_1, ALTERNATE_WILDCARD));
        assertEquals(expected1Wildcard, map.get(ITEM_1, OreDictionary.WILDCARD_VALUE));
        assertEquals(expected1Wildcard, map.get(ITEM_1, 1));
        // check item 1 key meta response
        assertEquals(expected1KeyMeta, map.get(ITEM_1, 0));
        // check item 2 responses
        assertEquals(expected20, map.get(ITEM_2, 0));
        assertEquals(expected21, map.get(ITEM_2, 1));
        assertNull(map.get(ITEM_2, 2));
        assertNull(map.get(ITEM_2, ALTERNATE_WILDCARD));
        assertNull(map.get(ITEM_2, OreDictionary.WILDCARD_VALUE));
        // item 3 shouldn't be in there
        assertNull(map.get(ITEM_3, 0));
        assertNull(map.get(ITEM_3, ALTERNATE_WILDCARD));
        assertNull(map.get(ITEM_3, OreDictionary.WILDCARD_VALUE));
    }

    // endregion get

    // region getOrDefault

    @ParameterizedTest
    @ValueSource(ints = { 0, ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void getOrDefault_ReturnsDefaultWhenNotFound(int meta) {
        final TestMap<Object> map = new TestMap<>();
        final Object expectedDefault = new Object();
        assertSame(expectedDefault, map.getOrDefault(ITEM_1, meta, expectedDefault));
    }

    @Test
    public void getOrDefault_ReturnsCorrectValue() {
        final TestMap<Integer> map = new TestMap<>();
        final int expected1Wildcard = 0;
        final int expected1KeyMeta = 1;
        final int expected20 = 2;
        final int expected21 = 3;
        final int expectedDefault = 4;
        map.putWildcard(ITEM_1, expected1Wildcard, false);
        map.put(ITEM_1, 0, expected1KeyMeta);
        map.put(ITEM_2, 0, expected20);
        map.put(ITEM_2, 1, expected21);
        // check item 1 wildcard responses
        assertEquals(expected1Wildcard, map.getOrDefault(ITEM_1, ALTERNATE_WILDCARD, expectedDefault));
        assertEquals(expected1Wildcard, map.getOrDefault(ITEM_1, OreDictionary.WILDCARD_VALUE, expectedDefault));
        assertEquals(expected1Wildcard, map.getOrDefault(ITEM_1, 1, expectedDefault));
        // check item 1 key meta response
        assertEquals(expected1KeyMeta, map.getOrDefault(ITEM_1, 0, expectedDefault));
        // check item 2 responses
        assertEquals(expected20, map.getOrDefault(ITEM_2, 0, expectedDefault));
        assertEquals(expected21, map.getOrDefault(ITEM_2, 1, expectedDefault));
        assertSame(expectedDefault, map.getOrDefault(ITEM_2, 2, expectedDefault));
        assertSame(expectedDefault, map.getOrDefault(ITEM_2, ALTERNATE_WILDCARD, expectedDefault));
        assertSame(expectedDefault, map.getOrDefault(ITEM_2, OreDictionary.WILDCARD_VALUE, expectedDefault));
        // item 3 shouldn't be in there
        assertSame(expectedDefault, map.getOrDefault(ITEM_3, 0, expectedDefault));
        assertSame(expectedDefault, map.getOrDefault(ITEM_3, ALTERNATE_WILDCARD, expectedDefault));
        assertSame(expectedDefault, map.getOrDefault(ITEM_3, OreDictionary.WILDCARD_VALUE, expectedDefault));
    }

    // endregion getOrDefault

    // region isEmpty

    @Test
    public void isEmpty_IsEmptyAfterCreation() {
        final TestMap<Integer> map = new TestMap<>();
        assertTrue(map.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2 })
    public void isEmpty_NotEmptyAfterKeyMetaInsertion(int type) {
        final TestMap<Boolean> map = new TestMap<>();
        switch (type) {
            case 0 -> map.put(ITEM_1, 0, true);
            case 1 -> assertTrue(map.putIfAbsent(ITEM_1, 0, true, false));
            default -> assertTrue(map.putIfAbsent(ITEM_1, 0, true, true));
        }
        assertFalse(map.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void isEmpty_EmptyAverKeyMetaRemoval(boolean removeValuesIfWildcard) {
        final TestMap<Boolean> map = new TestMap<>();
        map.put(ITEM_1, 0, true);
        map.put(ITEM_1, 1, true);
        map.put(ITEM_2, 0, true);
        map.put(ITEM_2, 1, true);
        assertTrue(map.remove(ITEM_1, 0, removeValuesIfWildcard));
        assertFalse(map.isEmpty());
        assertTrue(map.remove(ITEM_1, 1, removeValuesIfWildcard));
        assertFalse(map.isEmpty());
        assertTrue(map.remove(ITEM_2, 0, removeValuesIfWildcard));
        assertFalse(map.isEmpty());
        assertTrue(map.remove(ITEM_2, 1, removeValuesIfWildcard));
        assertTrue(map.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7 })
    public void isEmpty_NotEmptyAfterWildcardInsertion(int type) {
        final TestMap<Boolean> map = new TestMap<>();
        switch (type) {
            case 0 -> map.putWildcard(ITEM_1, true, false);
            case 1 -> map.putWildcard(ITEM_1, true, true);
            case 2 -> map.put(ITEM_1, ALTERNATE_WILDCARD, true);
            case 3 -> map.put(ITEM_1, OreDictionary.WILDCARD_VALUE, true);
            case 4 -> assertTrue(map.putIfAbsent(ITEM_1, ALTERNATE_WILDCARD, true, false));
            case 5 -> assertTrue(map.putIfAbsent(ITEM_1, OreDictionary.WILDCARD_VALUE, true, false));
            case 6 -> assertTrue(map.putIfAbsent(ITEM_1, ALTERNATE_WILDCARD, true, true));
            default -> assertTrue(map.putIfAbsent(ITEM_1, OreDictionary.WILDCARD_VALUE, true, true));
        }
        assertFalse(map.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    public void isEmpty_EmptyAfterWildcardRemoval(int type) {
        final boolean clearNonWildcards = type % 2 == 0;
        final boolean alsoRemoveExistingValues = type < 2;
        final TestMap<Boolean> map = new TestMap<>();
        map.putWildcard(ITEM_1, true, clearNonWildcards);
        map.putWildcard(ITEM_2, false, clearNonWildcards);
        // only remove part of the contents
        assertTrue(map.removeWildcard(ITEM_1, alsoRemoveExistingValues));
        assertFalse(map.isEmpty());
        // clear the last one
        assertTrue(map.removeWildcard(ITEM_2, alsoRemoveExistingValues));
        assertTrue(map.isEmpty());
    }

    @Test
    public void isEmpty_NotEmptyAfterWildcardOverride() {
        final TestMap<Boolean> map = new TestMap<>();
        map.put(ITEM_1, 0, true);
        map.putWildcard(ITEM_1, true, true);
        assertFalse(map.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    public void isEmpty_NotEmptyAfterWildcardRemovalWhenKeyMetaExists(int type) {
        final TestMap<Boolean> map = new TestMap<>();
        map.put(ITEM_1, 0, true);
        map.putWildcard(ITEM_1, true, false);
        switch (type) {
            case 0 -> assertTrue(map.removeWildcard(ITEM_1, false));
            case 1 -> assertTrue(map.remove(ITEM_1, ALTERNATE_WILDCARD, false));
            default -> assertTrue(map.remove(ITEM_1, OreDictionary.WILDCARD_VALUE, false));
        }
        assertFalse(map.isEmpty());
    }

    // endregion isEmpty

    // region containsKey

    @ParameterizedTest
    @ValueSource(ints = { 0, ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void containsKey_UnknownKeysReturnFalse(int meta) {
        final TestMap<Boolean> map = new TestMap<>();
        assertFalse(map.containsKey(ITEM_1, meta));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void containsKey_NotAffectedByOtherKeys(int meta) {
        final TestMap<Boolean> map = new TestMap<>();
        map.put(ITEM_1, 0, false);
        map.put(ITEM_1, 1, false);
        map.putWildcard(ITEM_2, true, false);
        assertFalse(map.containsKey(ITEM_3, meta));
    }

    @Test
    public void containsKey_KeyMetaExistsAfterPut() {
        final TestMap<Boolean> map = new TestMap<>();
        map.put(ITEM_1, 0, false);
        assertTrue(map.containsKey(ITEM_1, 0));
    }

    @Test
    public void containsKey_KeyMetaExistsAfterPutIfAbsent() {
        final TestMap<Boolean> map = new TestMap<>();
        assertTrue(map.putIfAbsent(ITEM_1, 0, false, false));
        assertTrue(map.containsKey(ITEM_1, 0));
    }

    @Test
    public void containsKey_KeyMetaExistsAfterPutWildcard() {
        final TestMap<Boolean> map = new TestMap<>();
        map.putWildcard(ITEM_1, false, false);
        assertTrue(map.containsKey(ITEM_1, 0));
    }

    @Test
    public void containsKey_AlwaysReturnTrueForAnyMetaWhenWildcardExists() {
        final TestMap<Boolean> map = new TestMap<>();
        map.put(ITEM_1, 0, false);
        assertTrue(map.containsKey(ITEM_1, 0));
        assertFalse(map.containsKey(ITEM_1, 1));
        map.putWildcard(ITEM_1, false, false);
        assertTrue(map.containsKey(ITEM_1, 0));
        assertTrue(map.containsKey(ITEM_1, 1));
    }

    // endregion containsKey

    // region remove

    @ParameterizedTest
    @ValueSource(ints = { 0, ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void remove_FailsIfNotFound(int meta) {
        final TestMap<Integer> map = new TestMap<>();
        map.putWildcard(ITEM_1, 0, false);
        map.put(ITEM_1, 0, 1);
        map.put(ITEM_2, 0, 2);
        map.put(ITEM_2, 1, 3);
        assertFalse(map.remove(ITEM_3, meta, false));
        assertFalse(map.remove(ITEM_3, meta, true));
    }

    @ParameterizedTest
    @ValueSource(ints = { ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void remove_AlsoRemovesAllKeyMetaValuesWhenRemoveValuesIfWildcardIsFalse(int oreDictMeta) {
        final TestMap<Integer> map = new TestMap<>();
        map.putWildcard(ITEM_1, 0, false);
        map.put(ITEM_1, 0, 0);
        map.putWildcard(ITEM_2, 0, false);
        map.put(ITEM_2, 0, 0);
        map.putWildcard(ITEM_3, 0, false);
        map.put(ITEM_4, 0, 0);
        // should be able to delete it
        assertTrue(map.remove(ITEM_1, oreDictMeta, true));
        // should not be able to delete it again
        assertFalse(map.remove(ITEM_1, oreDictMeta, true));
        // other item 1 keys should all be gone too
        assertFalse(map.containsKey(ITEM_1, 0));
        // other keys should not be affected
        assertTrue(map.containsKey(ITEM_2, oreDictMeta));
        assertTrue(map.containsKey(ITEM_3, 0));
        assertTrue(map.containsKey(ITEM_3, 0));
        assertTrue(map.containsKey(ITEM_3, oreDictMeta));
        assertTrue(map.containsKey(ITEM_4, 0));
    }

    @ParameterizedTest
    @ValueSource(ints = { ALTERNATE_WILDCARD, OreDictionary.WILDCARD_VALUE })
    public void remove_RemovesOnlyWildCardWhenRemoveValuesIfWildcardIsFalse(int oreDictMeta) {
        final TestMap<Integer> map = new TestMap<>();
        map.putWildcard(ITEM_1, 0, false);
        map.put(ITEM_1, 0, 0);
        map.putWildcard(ITEM_2, 0, false);
        map.put(ITEM_2, 0, 0);
        map.putWildcard(ITEM_3, 0, false);
        map.put(ITEM_4, 0, 0);
        // should be able to delete it
        assertTrue(map.remove(ITEM_1, oreDictMeta, false));
        // should not be able to delete it again
        assertFalse(map.remove(ITEM_1, oreDictMeta, false));
        // other keys should be unaffected
        assertTrue(map.containsKey(ITEM_1, 0));
        assertTrue(map.containsKey(ITEM_2, oreDictMeta));
        assertTrue(map.containsKey(ITEM_2, 0));
        assertTrue(map.containsKey(ITEM_3, 0));
        assertTrue(map.containsKey(ITEM_3, oreDictMeta));
        assertTrue(map.containsKey(ITEM_4, 0));
    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void remove_RemovesOnlyGivenKeyMeta(boolean removeValueIfWildcard) {
        final TestMap<Integer> map = new TestMap<>();
        final int expected1Wildcard = 1;
        final int expected10 = 2;
        final int expected11 = 3;
        map.putWildcard(ITEM_1, expected1Wildcard, false);
        map.put(ITEM_1, 0, expected10);
        map.put(ITEM_1, 1, expected11);
        map.putWildcard(ITEM_2, 0, false);
        map.put(ITEM_2, 0, 0);
        map.putWildcard(ITEM_3, 0, false);
        map.put(ITEM_4, 0, 0);
        // should be able to delete it
        assertTrue(map.remove(ITEM_1, 0, removeValueIfWildcard));
        // should not be able to delete it again
        assertFalse(map.remove(ITEM_1, 0, removeValueIfWildcard));
        // item 1 meta 1 should still respond since it has its own value
        assertEquals(expected11, map.get(ITEM_1, 1));
        // other queries to item 1 should still respond with the wildcard value
        assertEquals(expected1Wildcard, map.get(ITEM_1, 0));
        assertEquals(expected1Wildcard, map.get(ITEM_1, OreDictionary.WILDCARD_VALUE));
        assertEquals(expected1Wildcard, map.get(ITEM_1, ALTERNATE_WILDCARD));
        // other entries should be unaffected
        assertTrue(map.containsKey(ITEM_2, 0));
        assertTrue(map.containsKey(ITEM_2, OreDictionary.WILDCARD_VALUE));
        assertTrue(map.containsKey(ITEM_2, ALTERNATE_WILDCARD));
        assertTrue(map.containsKey(ITEM_3, 0));
        assertTrue(map.containsKey(ITEM_3, OreDictionary.WILDCARD_VALUE));
        assertTrue(map.containsKey(ITEM_3, ALTERNATE_WILDCARD));
        assertTrue(map.containsKey(ITEM_4, 0));
    }

    // endregion remove

    // region removeWildcard

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    public void removeWildcard_FailsIfNotFound(boolean alsoRemoveExistingValues) {
        final TestMap<Integer> map = new TestMap<>();
        map.putWildcard(ITEM_1, 0, false);
        map.put(ITEM_1, 0, 0);
        map.put(ITEM_2, 0, 0);
        assertFalse(map.removeWildcard(ITEM_3, alsoRemoveExistingValues));
    }

    @Test
    public void removeWildcard_AlsoRemovesAllKeyMetaValuesWhenRemoveValuesIfWildcardIsFalse() {
        final TestMap<Integer> map = new TestMap<>();
        map.putWildcard(ITEM_1, 0, false);
        map.put(ITEM_1, 0, 0);
        map.putWildcard(ITEM_2, 0, false);
        map.put(ITEM_2, 0, 0);
        map.putWildcard(ITEM_3, 0, false);
        map.put(ITEM_4, 0, 0);
        // should be able to delete it
        assertTrue(map.removeWildcard(ITEM_1, true));
        // should not be able to delete it again
        assertFalse(map.removeWildcard(ITEM_1, true));
        assertFalse(map.remove(ITEM_1, ALTERNATE_WILDCARD, true));
        assertFalse(map.remove(ITEM_1, OreDictionary.WILDCARD_VALUE, true));
        // other item 1 keys should all be gone too
        assertFalse(map.containsKey(ITEM_1, 0));
        // other keys should not be affected
        assertTrue(map.containsKey(ITEM_2, ALTERNATE_WILDCARD));
        assertTrue(map.containsKey(ITEM_2, OreDictionary.WILDCARD_VALUE));
        assertTrue(map.containsKey(ITEM_3, 0));
        assertTrue(map.containsKey(ITEM_3, 0));
        assertTrue(map.containsKey(ITEM_3, ALTERNATE_WILDCARD));
        assertTrue(map.containsKey(ITEM_3, OreDictionary.WILDCARD_VALUE));
        assertTrue(map.containsKey(ITEM_4, 0));
    }

    @Test
    public void removeWildcard_RemovesOnlyWildcardWhenRemoveValuesIfWildcardIsFalse() {
        final TestMap<Integer> map = new TestMap<>();
        map.putWildcard(ITEM_1, 0, false);
        map.put(ITEM_1, 0, 0);
        map.putWildcard(ITEM_2, 0, false);
        map.put(ITEM_2, 0, 0);
        map.putWildcard(ITEM_3, 0, false);
        map.put(ITEM_4, 0, 0);
        // should be able to delete it
        assertTrue(map.removeWildcard(ITEM_1, false));
        // should not be able to delete it again
        assertFalse(map.removeWildcard(ITEM_1, false));
        assertFalse(map.remove(ITEM_1, ALTERNATE_WILDCARD, false));
        assertFalse(map.remove(ITEM_1, OreDictionary.WILDCARD_VALUE, false));
        // other keys should be unaffected
        assertTrue(map.containsKey(ITEM_1, 0));
        assertTrue(map.containsKey(ITEM_2, ALTERNATE_WILDCARD));
        assertTrue(map.containsKey(ITEM_2, OreDictionary.WILDCARD_VALUE));
        assertTrue(map.containsKey(ITEM_2, 0));
        assertTrue(map.containsKey(ITEM_3, 0));
        assertTrue(map.containsKey(ITEM_3, ALTERNATE_WILDCARD));
        assertTrue(map.containsKey(ITEM_3, OreDictionary.WILDCARD_VALUE));
        assertTrue(map.containsKey(ITEM_4, 0));
    }

    // endregion removeWildcard
}
