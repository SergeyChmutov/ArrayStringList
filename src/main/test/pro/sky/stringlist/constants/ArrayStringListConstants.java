package pro.sky.stringlist.constants;

import pro.sky.stringlist.interfaces.StringList;
import pro.sky.stringlist.services.ArrayStringList;

public class ArrayStringListConstants {
    public static int ZERO_SIZE_STRING_LIST = 0;

    public static int NEGATIVE_CAPACITY = -1;
    public static int USER_DEFINED_CAPACITY = 5;

    public static String NULL_ITEM = null;

    public static String FIRST_ITEM = "first";
    public static String SECOND_ITEM = "second";
    public static String THIRD_ITEM = "third";
    public static String FOURTH_ITEM = "fourth";

    public static int NEGATIVE_INDEX = -2;
    public static int ZERO_INDEX = 0;
    public static int FIRST_INDEX = 1;
    public static int SECOND_INDEX = 2;
    public static int THIRD_INDEX = 3;

    public static int RETURN_VALUE_IF_ITEM_NOT_FOUND = -1;

    public static String[] EMPTY_STRING_ARRAY = new String[]{};
    public static String[] ARRAY_OF_TWO_FIRST_ITEMS = new String[]{FIRST_ITEM, SECOND_ITEM};

    public static StringList EMPTY_STRING_LIST_WITH_DEFAULT_CAPACITY = new ArrayStringList();
    public static StringList EMPTY_STRING_LIST_WITH_USER_DEFINED_CAPACITY = new ArrayStringList(USER_DEFINED_CAPACITY);
}
