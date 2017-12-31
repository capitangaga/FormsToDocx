package ru.kirillgolowko.formsToDocx.consoleApp;

import ru.kirillgolowko.formsToDocx.consoleApp.util.ApplicationParams;
import ru.kirillgolowko.formsToDocx.consoleApp.util.ArgumentsParser;

public class Main {
    private static ApplicationParams applicationParams;

    public static void main(String[] args) {
        ArgumentsParser  argumentsParser = new ArgumentsParser(args);
        applicationParams = argumentsParser.parse();
    }

}
