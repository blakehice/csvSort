package main.java;

import java.util.ArrayList;
import java.util.List;

public class CSVStringRow implements CSVRow<String>{

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
            fieldList.add(new CSVField<>(field));
        }
        return fieldList;
    }

}
