public enum CSVColumn {
    NAME(0),
    STATE(1),
    MOTTO(2),
    MAYOR(3);

    private final int sortNumber;

    CSVColumn(int sortNumber) {
        this.sortNumber = sortNumber;
    }

    public int getSortNumber() {
        return sortNumber;
    }

}
