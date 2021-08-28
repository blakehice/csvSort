package main.java;

import java.util.List;

public interface CSVRow<T> {
    int getLineNumber();
    List<CSVField<T>> getFields();
}
