package main.java.csv.columns;

/**
 * Standard city CSV header definition
 */
public enum CityCSVColumns implements CSVColumns{
    // == enum definition ==
    NAME(0),
    STATE(1),
    MOTTO(2),
    MAYOR(3);

    private final int sortNumber;

    // == constructor ==
    CityCSVColumns(int sortNumber) {
        this.sortNumber = sortNumber;
    }

    // == public methods ==
    @Override
    public int getSortNumber() {
        return sortNumber;
    }



}
