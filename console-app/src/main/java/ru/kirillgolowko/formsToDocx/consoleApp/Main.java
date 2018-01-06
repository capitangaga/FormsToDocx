package ru.kirillgolowko.formsToDocx.consoleApp;

import ru.kirillgolowko.formsToDocx.consoleApp.util.ApplicationParams;
import ru.kirillgolowko.formsToDocx.consoleApp.util.ArgumentsParser;
import ru.kirillgolowko.formsToDocx.consoleApp.util.SourceType;
import ru.kirillgolowko.formsToDocx.core.Processor;
import ru.kirillgolowko.formsToDocx.core.tables.CSVTable;
import ru.kirillgolowko.formsToDocx.core.tables.ITableProvider;

import java.io.File;

public class Main {
    private static ApplicationParams applicationParams;

    public static void main(String[] args) throws Exception{
        ArgumentsParser  argumentsParser = new ArgumentsParser(args);
        applicationParams = argumentsParser.parse();

        ITableProvider tableProvider = null;

        if(applicationParams.getSourceType().equals(SourceType.CSV)){
            File csvFile = new File(applicationParams.getSourcePath());

            if( ! ( csvFile.isFile() && csvFile.exists() ) ) {
                System.err.println("No such CSV file" + applicationParams.getSourcePath());
                System.exit(1);
            }

            tableProvider = new CSVTable(csvFile);
        } else {
            System.out.println("This source kind support will be in feature releases");
            System.exit(0);
        }

        Processor processor = new Processor(tableProvider, new File(applicationParams.getTemplatePath() ) );


    }

}
