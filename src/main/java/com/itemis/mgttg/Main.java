package com.itemis.mgttg;

import com.itemis.mgttg.view.ConsoleView;
import com.itemis.mgttg.view.Constants;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView(System.out);
        if(args.length == 2 && args[0].equals("-f")){
            view.run_fromFile(args[1]);
        }
        else if(args.length == 0) {
            view.run();
        }
        else{
            System.out.println(Constants.USAGE_INFO);
        }
    }
}
