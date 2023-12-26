package pro.sky.stringlist;

import pro.sky.stringlist.exceptions.StringListIndexOutOfBoundsException;
import pro.sky.stringlist.exceptions.StringListNullArgumentValueException;
import pro.sky.stringlist.interfaces.StringList;
import pro.sky.stringlist.services.ArrayStringList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StringList strings = new ArrayStringList();

        // add
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");

        strings.remove("5");
        strings.remove(0);

        //add by index
        strings.add(3, "5");

        // set
        strings.set(1, "6");

        System.out.println(Arrays.toString(strings.toArray()));

        // size
        System.out.println("size = " + strings.size());

        // contains
        System.out.println("strings.contains(\"6\") = " + strings.contains("6"));

        // indexOf
        System.out.println("strings.indexOf(\"1\") = " + strings.indexOf("1"));

        // lastIndexOf
        System.out.println("strings.lastIndexOf(\"5\") = " + strings.lastIndexOf("5"));

        // get
        System.out.println("strings.get(0) = " + strings.get(0));

        // equals(self)
        System.out.println("strings.equals(strings) = " + strings.equals(strings));

        // equals()
        ArrayStringList anotherStrings = new ArrayStringList(4);
        anotherStrings.add("1");

        System.out.println("strings.equals(anotherStrings) = " + strings.equals(anotherStrings));

        // clear() and isEmpty()
        strings.clear();
        System.out.println("strings.isEmpty() = " + strings.isEmpty());

        // throws
        try {
            anotherStrings.add(2, "");
        } catch (StringListIndexOutOfBoundsException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }

        try {
            anotherStrings.add(0, null);
        } catch (StringListNullArgumentValueException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
    }
}
