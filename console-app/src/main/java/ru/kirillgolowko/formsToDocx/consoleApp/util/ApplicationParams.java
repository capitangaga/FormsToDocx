package ru.kirillgolowko.formsToDocx.consoleApp.util;

public class ApplicationParams {

    /**
     * Csv, xlsx or google docs link
     */
    private SourceType sourceType;
    /**
     * Path to table
     */
    private String sourcePath;
    /**
     * Where to write generated files
     */
    private String outputFolderPath ;

    /**
     * Template file location
     */
    private String templatePath;

    /**
     * Params constructor
     * @param sourceType Csv, xlsx or google docs link
     * @param sourcePath  Path to table
     * @param templatePath Template file location
     */
    ApplicationParams(SourceType sourceType, String sourcePath, String templatePath){
        this.sourceType = sourceType;
        this.sourcePath = sourcePath;
        this.templatePath = templatePath;
        outputFolderPath = System.getProperty("user.dir");
    }

    /**
     * Returns source type
     * @return source type
     */
    public SourceType getSourceType() {
        return sourceType;
    }

    /**
     * Where is source table is
     * @return path
     */
    public String getSourcePath() {
        return sourcePath;
    }

    /**
     * Where to write generated files
     * @return path to folder
     */
    public String getOutputFolderPath() {
        return outputFolderPath;
    }

    /**
     * Where to write generated files
     * @param outputFolderPath path to folder
     */
    public void setOutputFolderPath(String outputFolderPath) {
        this.outputFolderPath = outputFolderPath;
    }

    /**
     * Where template file is
     * @return path
     */
    public String getTemplatePath() {
        return templatePath;
    }
}
