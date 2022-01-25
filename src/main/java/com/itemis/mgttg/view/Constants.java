package com.itemis.mgttg.view;

public class Constants {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    public static final String USAGE_INFO = "Usage: MGTTG.jar -f FILENAME or MGTTG.jar without parameters";
    static final String INTRO = "Greetings, traveller! If you need help, try entering \"help\"!";
    static final String OUTRO = "Until next time!";
    static final String HELP = "Available Commands: \n" +
            "list words\n\tLists all the words I've learned so far\n" +
            "list materials\n\tLists all the materials I know so far\n" +
            "file FILE\n\tReads from a File in this directory\n" +
            "WORD is <CHARACTER>\n\tAssigns a new word to a Character\n" +
            "WORD WORD ... WORD MATERIAL is NUMBER Credits\n\tLearns the Price of a new Material from a Trade\n" +
            "how much is WORD ... WORD ?\n\tGets the value of those Alien words\n" +
            "how many Credits is WORD ... WORD MATERIAL ?\n\tFinds out the price of so many units of the Material\n" +
            "help\n\tDisplays this text\n" +
            "the answer to life, the universe, and everything\n\tYou already know what it is, don't you";
    static final String INVALID_INPUT = "I have no idea what you are talking about";
    static final String LOGO = ANSI_BLUE+"\n" +
            "                  `.-://////:-.`                  \n" +
            "             `-+shdddhyyyyyyhdddyo/-`             \n" +
            "          `:shdhs+:.```   ````.:+shdho:`          \n" +
            "        .ohdy+-`   ``        `    `-ohdy/`        \n" +
            "      .ohdo-`    :syys:   `+yhys-    `:ydh/`      \n" +
            "     /hds-      :dddddd:  odddddh.     `:ydy-     \n" +
            "   `odh/`       -hddddh-  /dddddy`       `odh/    \n" +
            "  `odh-          .+oo+.    -+so/`          /dd/   \n" +
            "  +dh-                                      /dd:  \n" +
            " -dd/        ----------    ---------.        sdh` \n" +
            " sdh         hddddddddy   `ddddddddds        .dd/ \n" +
            " dd+         syyyhddddy   `dddddyyyy+         yds \n" +
            ".dd/             +ddddy   `ddddd-             odh \n" +
            ".dd:             +ddddy   `ddddd-             odh \n" +
            " dd+             +ddddy   `ddddd-             yds \n" +
            " sdh             +ddddy   `ddddd-            .dd/ \n" +
            " -dd/            +ddddy   `ddddd-            sdh` \n" +
            "  +dh-           +ddddy   `ddddd-           /dd:  \n" +
            "  `odh-          +ddddy   `ddddd-          /dd/   \n" +
            "   `odh/`        +ddddy   `ddddd-        `odh/    \n" +
            "     /hds-       +ddddy   `ddddd-      `:ydy-     \n" +
            "      .ohdo-`    .::::-    :::::`    `:sdh+`      \n" +
            "        -ohdy+.`                  `-ohdy+.        \n" +
            "          `/shdys+:.```   ````.:+shdho:`          \n" +
            "             `-+shdddhyyyyyyhdddyo/-`             \n" +
            "                  `.-://+///:-.`                  "+ANSI_RESET;
}
