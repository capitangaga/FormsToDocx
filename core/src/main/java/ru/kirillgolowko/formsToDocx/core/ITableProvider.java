package ru.kirillgolowko.formsToDocx.core;

import javafx.util.Pair;

import java.io.EOFException;
import java.util.List;

public interface ITableProvider {
    /**
     * Method to get table first line as a header
     * @return list of headers
     */
    List<String> getHeaders();

    /**
     * Returns list of pairs (Header, Value) for the next table row
     * @return list of pairs
     */
    List<Pair<String, String>> getNextLineWithHeaders() throws EOFException;

    /**
     * Returns list of line values
     * @return list of values
     */
    List<String> getNextLineWithoutHeaders() throws EOFException;

    /**
     * Is there next line to read
     * @return true or false
     */
    boolean hasNext();

    /**
     * How many columns are in file
     * @return count of columns
     */
    int getColumnCount();

}
