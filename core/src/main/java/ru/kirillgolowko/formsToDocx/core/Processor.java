package ru.kirillgolowko.formsToDocx.core;

import ru.kirillgolowko.formsToDocx.core.tables.ITableProvider;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.List;

public class Processor {

    private ITableProvider table;

    private XWPFDocument template;

    public Processor(ITableProvider table, File template) throws IOException {

        if( ! template.exists() || ! template.isFile() )
            throw new FileNotFoundException( "No such file : " + template.getPath() );

        this.table = table;

        this.template = new XWPFDocument(new FileInputStream(template));

    }
}
