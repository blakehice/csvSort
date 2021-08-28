package main.java.csv.rows;

import main.java.csv.fields.CSVField;
import main.java.csv.columns.CSVColumns;
import main.java.csv.fields.StringCSVField;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CSVStringRow implements CSVRow<String> {

    // == fields ==
    private final int lineNumber;
    private final String lineText;
    private final List<CSVField<String>> fields;

    // == constructors ==
    public CSVStringRow(String lineText, int lineNumber) {
        this.lineNumber = lineNumber;
        this.lineText = lineText;
        this.fields = setFields();
    }

    // == public methods==
    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public List<CSVField<String>> getFields() {
        return fields;
    }

    @Override
    public String toString(){
        return lineText;
    }

    // define new comparator so we can sort the records based on the given column
    @Override
    public Comparator<CSVRow<String>> getComparator(CSVColumns sortColumn) {
        return new Comparator<>() {
            @Override
            public int compare(CSVRow<String> thisRecord, CSVRow<String> otherRecord) {
                return thisRecord.getFields().get(sortColumn.getSortNumber()).compareTo(otherRecord.getFields().get(sortColumn.getSortNumber()).getContent());
            }
        };
    }

    public String getLineText() {
        return lineText;
    }

    public boolean rowContentEqualsIgnoreCase(CSVStringRow otherRow){
        return this.lineText.equalsIgnoreCase(otherRow.getLineText());
    }


    // == private methods ==

    /**
     * This function takes the row text and creates a list representation of the data
     * @return list of csv fields representing row text
     */
    private List<CSVField<String>> setFields(){
        List<CSVField<String>> fieldList = new ArrayList<>();
        for(String field : lineText.split(",")){
            fieldList.add(new StringCSVField(field));
        }
        return fieldList;
    }

}
