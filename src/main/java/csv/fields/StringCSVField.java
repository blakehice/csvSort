package main.java.csv.fields;

public class StringCSVField implements CSVField<String> {

    private final String content;

    public StringCSVField(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public int compareTo(String s) {
        return this.getContent().compareTo(s);
    }
}
