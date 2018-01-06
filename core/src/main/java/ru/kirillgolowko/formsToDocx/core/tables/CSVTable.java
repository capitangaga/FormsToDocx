package ru.kirillgolowko.formsToDocx.core.tables;

import javafx.util.Pair;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class CSVTable implements ITableProvider {

    Iterator<CSVRecord> recordsIterator;

    List<String> headers;

    int columnCount = 0;

    public CSVTable(File csvFile) throws IOException{
            CSVParser parsedFile = CSVParser.parse(csvFile, Charset.defaultCharset(), CSVFormat.INFORMIX_UNLOAD_CSV);
            recordsIterator = parsedFile.iterator();
            if(recordsIterator.hasNext()){
                CSVRecord headerRecord = recordsIterator.next();

                columnCount = headerRecord.size();

                headers = new Vector<>();
                for (int i = 0; i < columnCount; ++i){
                    headers.add(headerRecord.get(i));
                }
            }
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public List<Pair<String, String>> getNextLineWithHeaders() throws EOFException{
        List<String> lineValues = getNextLineWithoutHeaders();
        List<Pair<String, String>> lineWithHeaders = new Vector<Pair<String, String>>();

        Pair<Iterator<String>, Iterator<String>> headersValuesIterators =
                new Pair<Iterator<String>, Iterator<String>>(headers.iterator(), lineValues.iterator());

        while (headersValuesIterators.getKey().hasNext() && headersValuesIterators.getValue().hasNext()){
            lineWithHeaders.add( new Pair<String, String>(
                            headersValuesIterators.getKey().next(),
                            headersValuesIterators.getValue().next())
            );
        }

        return lineWithHeaders;
    }

    @Override
    public List<String> getNextLineWithoutHeaders() throws EOFException{
        if(recordsIterator.hasNext()){
            List<String> values = new Vector<>();
            CSVRecord next = recordsIterator.next();
            if(next.size() != columnCount)
                throw new NumberFormatException("Line is not in CSV format");
            for (int i = 0; i < columnCount; ++i){
                values.add(next.get(i));
            }
            return values;
        } else
            throw new EOFException("No more strings to read");
    }

    @Override
    public boolean hasNext() {
        return recordsIterator.hasNext();
    }

    @Override
    public List<String> getHeaders() {
        return headers;
    }
}