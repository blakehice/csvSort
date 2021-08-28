package main.java;

import java.util.ArrayList;
import java.util.List;

public class CSVFile<T> {

    private CSVRow<T> header;
    private List<CSVRow<T>> records;

    public CSVFile() {
        this.records = new ArrayList<>();
    }

    public CSVRow<T> getHeader() {
        return header;
    }

    public void setHeader(CSVRow<T> header) {
        this.header = header;
    }

    public List<CSVRow<T>> getRecords() {
        return records;
    }

    public void setRecords(List<CSVRow<T>> records) {
        this.records = records;
    }
}
