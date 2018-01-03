package ru.kirillgolowko.formsToDocx.consoleApp;

import javafx.util.Pair;
import ru.kirillgolowko.formsToDocx.consoleApp.util.ApplicationParams;
import ru.kirillgolowko.formsToDocx.consoleApp.util.ArgumentsParser;
import ru.kirillgolowko.formsToDocx.consoleApp.util.SourceType;
import ru.kirillgolowko.formsToDocx.core.CSVTable;
import ru.kirillgolowko.formsToDocx.core.ITableProvider;

import java.io.File;

public class Main {
    private static ApplicationParams applicationParams;

    public static void main(String[] args) throws Exception{
        ArgumentsParser  argumentsParser = new ArgumentsParser(args);
        applicationParams = argumentsParser.parse();

        ITableProvider tableProvider = null;

        if(applicationParams.getSourceType().equals(SourceType.CSV)){
            File csvFile = new File(applicationParams.getSourcePath());
            tableProvider = new CSVTable(csvFile);
        } else {
            System.out.println("This source kind support will be in feature releases");
        }

    }

}
