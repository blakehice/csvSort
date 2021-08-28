package main.java.csv.rows;

import main.java.csv.fields.CSVField;
import main.java.csv.columns.CSVColumns;

import java.util.Comparator;
import java.util.List;

public interface CSVRow<T> {
    int getLineNumber();
    List<CSVField<T>> getFields();
    Comparator<CSVRow<T>> getComparator(CSVColumns sortColumn);
}
