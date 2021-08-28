package main.java;

public class CSVField<T> {

    // == fields ==
    private final T content;

    // == constructors ==
    public CSVField(T content) {
        this.content = content;
    }

    // == public methods ==
    public T getContent() {
        return content;
    }

}
