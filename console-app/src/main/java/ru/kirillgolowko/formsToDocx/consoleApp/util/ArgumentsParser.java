package ru.kirillgolowko.formsToDocx.consoleApp.util;

import org.apache.commons.cli.*;

public class ArgumentsParser {

    /**
     * CL args
     */
    String[] args;

    /**
     * Options set
     */
    Options options;

    /**
     * Creates options parser from args
     * @param args args
     */
    public ArgumentsParser(String[] args){

        this.args = args;


        options = new Options();

        OptionGroup sourceTypeOptionGroup = new OptionGroup();

        Option csvOption = new Option("c", "csv", true,
                "Path to csv source table");
        csvOption.setArgs(1);
        csvOption.setRequired(false);

        Option linkOption = new Option("l", "link", true,
                "Link to Google docs doc");
        linkOption.setArgs(1);
        linkOption.setRequired(false);

        Option xmlsOption = new Option("x", "xmls", true,
                "Path to xls table");
        xmlsOption.setArgs(1);
        xmlsOption.setRequired(false);

        sourceTypeOptionGroup.addOption(csvOption);
        sourceTypeOptionGroup.addOption(linkOption);
        sourceTypeOptionGroup.addOption(xmlsOption);
        sourceTypeOptionGroup.setRequired(true);

        options.addOptionGroup(sourceTypeOptionGroup);

        OptionGroup templateOptions = new OptionGroup();
        Option templatePathOption = new Option("t", "template", true,
                "Path to template docx file") ;
        templatePathOption.setRequired(true);
        templatePathOption.setArgs(1);
        templateOptions.addOption(templatePathOption);
        options.addOptionGroup(templateOptions);

        OptionGroup outPathOptions = new OptionGroup();
        Option outputFolderPathOption = new Option("o", "outpath", true,
                "Path to generated files folder") ;
        outputFolderPathOption.setRequired(false);
        outputFolderPathOption.setArgs(1);
        outPathOptions.addOption(outputFolderPathOption);
        options.addOptionGroup(outPathOptions);

    }

    /**
     * Parses options
     * @return params
     */
    public ApplicationParams parse(){
        CommandLineParser parser = new DefaultParser();
        try{

            CommandLine line = parser.parse(options, args);
            SourceType sourceType = null;
            String sourcePath = null;

            if(line.hasOption('c')){
                sourceType = SourceType.CSV;
                sourcePath = line.getOptionValue('c');
            } else if(line.hasOption('l')){
                sourceType = SourceType.Link;
                sourcePath = line.getOptionValue('l');
            } else if(line.hasOption('x')){
                sourceType = SourceType.XLSX;
                sourcePath = line.getOptionValue('x');
            }

            String templatePath = line.getOptionValue('t');

            ApplicationParams applicationParams = new ApplicationParams(sourceType, sourcePath, templatePath);

            if(line.hasOption('o'))
                applicationParams.setOutputFolderPath(line.getOptionValue('o'));

            return applicationParams;
        } catch (ParseException ex){
            System.err.println("Params parsing failed: " + ex.getMessage());
            throw new Error("params check failed");
        }
    }
}
