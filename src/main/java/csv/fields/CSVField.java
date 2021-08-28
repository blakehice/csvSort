package main.java.csv.fields;

public interface CSVField<T> extends Comparable<T> {

    // == public methods ==
    T getContent();

}
