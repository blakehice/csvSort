package main.java.csv;

import main.java.csv.columns.CSVColumns;
import main.java.csv.rows.CSVRow;

import java.util.ArrayList;
import java.util.List;

public class CSVFile<T> {

    // == fields ==
    private CSVRow<T> header;
    private List<CSVRow<T>> records;


    // == public methods ==
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        if(header != null){
            builder.append(header);
            for(CSVRow<T> row : records){
                builder.append("\n");
                builder.append(row.toString());
            }
        }
        return builder.toString();
    }

    public void sortRowsByColumn(CSVColumns sortColumn){

        if(records == null || records.size() == 0){
            return;
        }
        records.sort(records.get(0).getComparator(sortColumn));
    }

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
