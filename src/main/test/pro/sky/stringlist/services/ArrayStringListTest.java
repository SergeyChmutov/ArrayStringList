package pro.sky.stringlist.services;

import org.junit.jupiter.api.Test;
import pro.sky.stringlist.exceptions.*;
import pro.sky.stringlist.interfaces.StringList;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.stringlist.constants.ArrayStringListConstants.*;

public class ArrayStringListTest {

    private final StringList out = new ArrayStringList();

    // constructor

    @Test
    public void shouldThrowStringListStorageInitializationExceptionWhenCapacityIsNegative() {
        assertThrows(StringListStorageInitializationException.class, () -> new ArrayStringList(NEGATIVE_CAPACITY));
    }

    // add method

    @Test
    public void shouldThrowStringListNullArgumentValueExceptionWhenAddMethodCallWithNullItem() {
        assertThrows(StringListNullArgumentValueException.class, () -> out.add(NULL_ITEM));
    }

    @Test
    public void shouldThrowStringListIndexOutOfBoundsExceptionWhenAddMethodCallWithIndexNotInListRange() {
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.add(NEGATIVE_INDEX, FIRST_ITEM));
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.add(FIRST_INDEX, FIRST_ITEM));
    }

    @Test
    public void shouldReturnItemWhenAddMethodCallWithValidIndexAndItem() {
        assertEquals(out.add(SECOND_ITEM), SECOND_ITEM);
        assertEquals(out.add(ZERO_INDEX, FIRST_ITEM), FIRST_ITEM);

        assertArrayEquals(out.toArray(), ARRAY_OF_TWO_FIRST_ITEMS);
    }

    // set method

    @Test
    public void shouldThrowStringListNullArgumentValueExceptionWhenSetMethodCallWithNullItem() {
        out.add(FIRST_ITEM);
        assertThrows(StringListNullArgumentValueException.class, () -> out.set(0, NULL_ITEM));
    }

    @Test
    public void shouldThrowStringListIndexOutOfBoundsExceptionWhenSetMethodCallWithIndexNotInListRange() {
        out.add(FIRST_ITEM);
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.set(FIRST_INDEX, SECOND_ITEM));
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.set(NEGATIVE_INDEX, SECOND_ITEM));
    }

    @Test
    public void shouldReturnItemWhenSetMethodCallWithValidIndexAndItem() {
        out.add(SECOND_ITEM);
        out.add(SECOND_ITEM);
        assertEquals(out.set(0, FIRST_ITEM), FIRST_ITEM);

        assertArrayEquals(out.toArray(), ARRAY_OF_TWO_FIRST_ITEMS);
    }

    // remove method

    @Test
    public void shouldThrowStringListNullArgumentValueExceptionWhenRemoveMethodCallWithNullItem() {
        assertThrows(StringListNullArgumentValueException.class, () -> out.remove(NULL_ITEM));
    }

    @Test
    public void shouldThrowStringListItemNotFoundExceptionWhenRemoveMethodCallWithEmptyList() {
        assertThrows(StringListItemNotFoundException.class, () -> out.remove(FIRST_ITEM));
    }

    @Test
    public void shouldThrowStringListItemNotFoundExceptionWhenRemoveMethodCallWithListNotContainsItem() {
        out.add(SECOND_ITEM);
        assertThrows(StringListItemNotFoundException.class, () -> out.remove(FIRST_ITEM));
    }

    @Test
    public void shouldReturnItemWhenRemoveMethodCallWithListContainsItem() {
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);
        assertEquals(out.remove(THIRD_ITEM), THIRD_ITEM);
        assertEquals(out.remove(SECOND_ITEM), SECOND_ITEM);

        assertArrayEquals(out.toArray(), EMPTY_STRING_ARRAY);
    }

    @Test
    public void shouldThrowStringListIndexOutOfBoundsExceptionWhenRemoveMethodCallWithIndexNotInListRange() {
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.remove(ZERO_INDEX));
        out.add(FIRST_ITEM);
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.remove(FIRST_INDEX));
    }

    @Test
    public void shouldReturnItemWhenRemoveMethodCallWithValidIndex() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);

        assertEquals(out.remove(SECOND_INDEX), THIRD_ITEM);
        assertEquals(out.remove(FIRST_INDEX), SECOND_ITEM);
        assertEquals(out.remove(ZERO_INDEX), FIRST_ITEM);

        assertArrayEquals(out.toArray(), EMPTY_STRING_ARRAY);
    }

    // contains method

    @Test
    public void shouldThrowStringListNullArgumentValueExceptionWhenContainsMethodCallWithNullItem() {
        assertThrows(StringListNullArgumentValueException.class, () -> out.contains(NULL_ITEM));
    }

    @Test
    public void shouldReturnFalseWhenContainsMethodCallWithEmptyList() {
        assertFalse(out.contains(FIRST_ITEM));
    }

    @Test
    public void shouldReturnFalseWhenContainsMethodCallWithListNotContainsItem() {
        out.add(SECOND_ITEM);
        assertFalse(out.contains(FIRST_ITEM));
    }

    @Test
    public void shouldReturnTrueWhenContainsMethodCallWithListContainsItem() {
        out.add(FIRST_ITEM);
        assertTrue(out.contains(FIRST_ITEM));
    }

    // indexOf method

    @Test
    public void shouldThrowStringListNullArgumentValueExceptionWhenIndexOfMethodCallWithNullItem() {
        assertThrows(StringListNullArgumentValueException.class, () -> out.indexOf(NULL_ITEM));
    }

    @Test
    public void shouldReturnValueIfItemNotFoundWhenIndexOfMethodCallWithEmptyList() {
        assertEquals(out.indexOf(FIRST_ITEM), RETURN_VALUE_IF_ITEM_NOT_FOUND);
    }

    @Test
    public void shouldReturnValueIfItemNotFoundWhenIndexOfMethodCallWithListNotContainsItem() {
        out.add(SECOND_ITEM);
        assertEquals(out.indexOf(FIRST_ITEM), RETURN_VALUE_IF_ITEM_NOT_FOUND);
    }

    @Test
    public void shouldReturnFirstIndexOfItemWhenIndexOfMethodCallWithListContainsFewFoundItems() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);
        out.add(SECOND_ITEM);
        assertEquals(out.indexOf(SECOND_ITEM), FIRST_INDEX);
    }

    // lastIndexOf method

    @Test
    public void shouldThrowStringListNullArgumentValueExceptionWhenLastIndexOfMethodCallWithNullItem() {
        assertThrows(StringListNullArgumentValueException.class, () -> out.lastIndexOf(NULL_ITEM));
    }

    @Test
    public void shouldReturnValueIfItemNotFoundWhenLastIndexOfMethodCallWithEmptyList() {
        assertEquals(out.lastIndexOf(FIRST_ITEM), RETURN_VALUE_IF_ITEM_NOT_FOUND);
    }

    @Test
    public void shouldReturnValueIfItemNotFoundWhenLastIndexOfMethodCallWithListNotContainsItem() {
        out.add(SECOND_ITEM);
        assertEquals(out.lastIndexOf(FIRST_ITEM), RETURN_VALUE_IF_ITEM_NOT_FOUND);
    }

    @Test
    public void shouldReturnFirstIndexOfItemWhenLastIndexOfMethodCallWithListContainsFewFoundItems() {
        out.add(THIRD_ITEM);
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);
        out.add(FOURTH_ITEM);
        assertEquals(out.lastIndexOf(THIRD_ITEM), SECOND_INDEX);
    }

    // get method

    @Test
    public void shouldThrowStringListIndexOutOfBoundsExceptionWhenGetMethodCallWithEmptyList() {
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.get(0));
    }

    @Test
    public void shouldThrowStringListIndexOutOfBoundsExceptionWhenGetMethodCallWithIndexNotInListRange() {
        out.add(FIRST_ITEM);
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.get(FIRST_INDEX));
        assertThrows(StringListIndexOutOfBoundsException.class, () -> out.get(NEGATIVE_INDEX));
    }

    @Test
    public void shouldReturnItemWhenGetMethodCallWithValidIndex() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        assertEquals(out.get(ZERO_INDEX), FIRST_ITEM);
        assertEquals(out.get(FIRST_INDEX), SECOND_ITEM);
    }

    // equals method

    @Test
    public void shouldThrowStringListNullArgumentValueExceptionWhenEqualsMethodCallWithNullStringList() {
        assertThrows(StringListNullArgumentValueException.class, () -> out.equals(null));
    }

    @Test
    public void shouldReturnTrueWhenEqualsMethodCallWithSelfStringList() {
        assertTrue(out.equals(out));
    }

    @Test
    public void shouldReturnTrueWhenEqualsMethodCallWithTwoEmptyLists() {
        assertTrue(out.equals(EMPTY_STRING_LIST_WITH_DEFAULT_CAPACITY));
        assertTrue(out.equals(EMPTY_STRING_LIST_WITH_USER_DEFINED_CAPACITY));
        assertTrue(EMPTY_STRING_LIST_WITH_DEFAULT_CAPACITY.equals(EMPTY_STRING_LIST_WITH_USER_DEFINED_CAPACITY));
    }

    @Test
    public void shouldReturnFalseWhenEqualsMethodCallWithTwoStringListWithDifferentNumberOfElements() {
        out.add(FIRST_ITEM);

        StringList anotherStringList = new ArrayStringList();

        anotherStringList.add(FIRST_ITEM);
        anotherStringList.add(FOURTH_ITEM);

        assertFalse(out.equals(anotherStringList));
    }

    @Test
    public void shouldReturnFalseWhenEqualsMethodCallWithTwoStringListWithDifferentOrderOfElements() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);

        StringList anotherStringList = new ArrayStringList();

        anotherStringList.add(SECOND_ITEM);
        anotherStringList.add(FIRST_ITEM);

        assertFalse(out.equals(anotherStringList));
    }

    @Test
    public void shouldReturnTrueWhenEqualsMethodCallWithTwoStringListWithIdenticalOrderAndElements() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);

        StringList anotherStringList = new ArrayStringList();

        anotherStringList.add(FIRST_ITEM);
        anotherStringList.add(SECOND_ITEM);

        assertTrue(out.equals(anotherStringList));
        assertTrue(anotherStringList.equals(out));
    }

    // size method

    @Test
    public void shouldReturnZeroSizeWhenSizeMethodCallWithEmptyList() {
        assertEquals(out.size(), ZERO_SIZE_STRING_LIST);
        assertEquals(EMPTY_STRING_LIST_WITH_DEFAULT_CAPACITY.size(), ZERO_SIZE_STRING_LIST);
        assertEquals(EMPTY_STRING_LIST_WITH_USER_DEFINED_CAPACITY.size(), ZERO_SIZE_STRING_LIST);
    }

    @Test
    public void shouldReturnActualSizeWhenSizeMethodCall() {
        out.add(FIRST_ITEM);
        assertEquals(out.size(), 1);

        out.add(SECOND_ITEM);
        assertEquals(out.size(), 2);

        out.add(THIRD_ITEM);
        assertEquals(out.size(), 3);

        out.remove(FIRST_ITEM);
        assertEquals(out.size(), 2);

        out.remove(ZERO_INDEX);
        assertEquals(out.size(), 1);
    }

    // isEmpty method

    @Test
    public void shouldReturnTrueWhenIsEmptyMethodCallWithEmptyList() {
        assertTrue(out.isEmpty());
        assertTrue(EMPTY_STRING_LIST_WITH_DEFAULT_CAPACITY.isEmpty());
        assertTrue(EMPTY_STRING_LIST_WITH_USER_DEFINED_CAPACITY.isEmpty());
    }

    @Test
    public void shouldReturnFalseWhenIsEmptyMethodCallWithNotEmptyList() {
        out.add(FIRST_ITEM);
        out.set(ZERO_INDEX, FOURTH_ITEM);

        assertFalse(out.isEmpty());
    }

    // clear method

    @Test
    public void shouldReturnZeroSizeWhenClearMethodCall() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        out.add(THIRD_ITEM);
        out.add(FOURTH_ITEM);

        out.clear();

        assertEquals(out.size(), ZERO_SIZE_STRING_LIST);
        assertTrue(out.equals(EMPTY_STRING_LIST_WITH_DEFAULT_CAPACITY));

        assertArrayEquals(out.toArray(), EMPTY_STRING_ARRAY);
        assertEquals(out.toArray().length, ZERO_SIZE_STRING_LIST);
    }

    // toArray method

    @Test
    public void shouldReturnEmptyArrayWhenToArrayMethodCallWithEmptyList() {
        assertArrayEquals(out.toArray(), EMPTY_STRING_ARRAY);
    }

    @Test
    public void shouldReturnArrayWhenToArrayMethodCall() {
        out.add(FIRST_ITEM);
        out.add(SECOND_ITEM);
        assertArrayEquals(out.toArray(), ARRAY_OF_TWO_FIRST_ITEMS);
    }
}
