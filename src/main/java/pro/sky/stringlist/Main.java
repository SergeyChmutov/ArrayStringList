package pro.sky.stringlist;

import pro.sky.stringlist.interfaces.StringList;
import pro.sky.stringlist.services.ArrayStringList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> listOfString = new ArrayList<>(5);

        listOfString.add(0, "1");
        listOfString.set(0, "3");
        listOfString.remove("1");
        listOfString.remove(0);

        System.out.println(listOfString);

        StringList strings = new ArrayStringList(1);

        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.remove(2);
        strings.remove(1);
        strings.remove(0);
        System.out.println(Arrays.toString(strings.toArray()));
    }
}
