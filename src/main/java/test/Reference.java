package test;

import java.util.ArrayList;
import java.util.List;

public class Reference {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("amayn");

        myFunc(list);

        System.out.println(list);
    }

    private static void myFunc(List<String> list) {
        list = new ArrayList<>();
        list.add("asldfj");
    }
}
