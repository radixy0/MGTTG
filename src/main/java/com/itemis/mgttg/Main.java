package com.itemis.mgttg;

import com.itemis.mgttg.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView(System.out);
        view.run();
    }
}
