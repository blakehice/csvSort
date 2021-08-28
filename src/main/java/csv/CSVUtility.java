package main.java.csv;

import main.java.csv.columns.CSVColumns;
import main.java.csv.columns.CityCSVColumns;
import main.java.csv.rows.CSVStringRow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CSVUtility {

    // when we create more csv column enums we can use this method to dynamically handle reading the csv file
    public static CSVStringRow getCorrectHeader(){
        // to verify the correct file, we can dynamically obtain the columns defined in the enum class and use them to
        // check the file header, after removing the included '[', ']', and ' ' (spaces) from the array to string output
        return  new CSVStringRow(Arrays.toString(CityCSVColumns.values())
                .replace("[","").replace("]","").replace(" ",""), -1);
    }

    public static CSVFile<String> loadStringCSVFile(String inputFile){

        Scanner fileScanner;
        CSVFile<String> csvFile = new CSVFile<>(); // csv file representation
        try {
            fileScanner = new Scanner(new File(inputFile));
        }catch (FileNotFoundException fne){
            System.out.println("FATAL ERROR!\nFile: '" + inputFile + "' not found");
            return null;
        }
        // header file should be line number 0
        csvFile.setHeader(fileScanner.hasNext() ? new CSVStringRow(fileScanner.nextLine(), 0) : null);
        //if the first line of the header matches our pre-defined header then we should proceed, else print error msg
        if(csvFile.getHeader() instanceof  CSVStringRow && CSVUtility.getCorrectHeader().rowContentEqualsIgnoreCase((CSVStringRow) csvFile.getHeader())) {
            // we are going to want to loop through the entire file and hold contents in their own string row
            int i = 1;
            while (fileScanner.hasNext()) {
                CSVStringRow currentLine = new CSVStringRow(fileScanner.nextLine(), i++);
                csvFile.getRecords().add(currentLine);
            }

        }else{
            System.out.println("ERROR: Expected header " + getCorrectHeader() + " does not match actual header " + csvFile.getHeader());
        }
        return csvFile;
    }

    public static CSVColumns getSortColumn(String[] args){
        String userInputColumn;
        // handles input from command line arg
        if(args.length > 0) {
            System.out.println(args[0]);
            userInputColumn = args[0];
            // handles input from console
        }else{
            Scanner consoleInput = new Scanner(System.in);
            System.out.println("please enter sort column. Available choices:\n");
            System.out.println(getCorrectHeader() + "\n");
            userInputColumn = consoleInput.nextLine();
        }

        // this logic is to figure out what column to sort by, we can use the enum sortNumber value
        // to represent index to sort by in the list
        CityCSVColumns sortColumn;
        try {
            sortColumn = CityCSVColumns.valueOf(userInputColumn.toUpperCase());
        }catch (IllegalArgumentException iae){
            System.out.println("Could not find sort column '" + userInputColumn + "' in accepted list of values.");
            return null;
        }
        System.out.println("Sorting list by : " + sortColumn + "...");

        return sortColumn;
    }

    public static void writeStringCsvFile(CSVFile<String> csvFile, String outputFile) throws IOException {
        FileWriter fileWriter = new FileWriter(outputFile);
        fileWriter.write(csvFile.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}
