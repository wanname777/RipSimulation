package com.zhang;

import java.util.Vector;

public class Test {
    public static void main(String[] args) {
        Vector<Integer> integers = new Vector<>();
        integers.add(1);
        integers.add(2);
        Vector<Integer> clone = (Vector<Integer>) integers.clone();
        clone.set(0,6);
        System.out.println(integers);
        System.out.println(clone);
    }
}
