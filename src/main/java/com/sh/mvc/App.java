package com.sh.mvc;

public class App {
    public int sum(int a, int b) {
        return a + b;
    }

    public int random() {
//        int n = (int) (Math.random() * 100) + 1;
//        return n;

        //refactoring
        return (int) (Math.random() * 100) + 1;
    }
}
