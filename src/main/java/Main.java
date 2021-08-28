package main.java;

import main.java.csv.CSVFile;
import main.java.csv.CSVUtility;
import main.java.csv.columns.CSVColumns;

import java.io.IOException;

public class Main {
    // main method to run the program
    public static void main(String[] args) {

        // == start constants section ==
        final String INPUT_FILE_NAME = "src/main/resources/input.csv";
        final String OUTPUT_FILE_NAME = "src/main/resources/output.csv";
        final boolean debug = false;
        // == end constants section ==

        CSVFile<String> csvFile = CSVUtility.loadStringCSVFile(INPUT_FILE_NAME);

        if(csvFile != null){
            CSVColumns sortColumn = CSVUtility.getSortColumn(args);
            if(sortColumn != null) {

                if(debug) {
                    System.out.println("\norder before sort\n");
                    System.out.println(csvFile);
                }

                csvFile.sortRowsByColumn(sortColumn);

                if(debug) {
                    System.out.println("\norder after sort\n");
                    System.out.println(csvFile);
                }

                try {
                    CSVUtility.writeStringCsvFile(csvFile, OUTPUT_FILE_NAME);
                }catch (IOException e) {
                    System.out.println("Failed to write file. Exception is " + e.getMessage());
                }
            }
        }
        System.out.println("Exiting application...");
    }
}
