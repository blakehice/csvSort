package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    // main method to run the program
    public static void main(String[] args) {

        // == start constants section ==
        // to verify the correct file, we can dynamically obtain the columns defined in the enum class and use them to
        // check the file header, after removing the included '[', ']', and ' ' (spaces) from the array to string output
        final CSVStringRow CORRECT_HEADER = new CSVStringRow(Arrays.toString(CSVColumn.values())
                .replace("[","").replace("]","").replace(" ",""), -1);
        final String INPUT_FILE_NAME = "src/main/resources/input.csv";
        final String OUTPUT_FILE_NAME = "output.csv";
        // == end constants section ==

        Scanner fileScanner;
        Scanner consoleInput = new Scanner(System.in);
        try {
            fileScanner = new Scanner(new File(INPUT_FILE_NAME));
        }catch (FileNotFoundException fne){
            System.out.println("FATAL ERROR!\nFile: '" + INPUT_FILE_NAME + "' not found");
            System.out.println("Exiting application...");
            return;
        }
        CSVFile<String> csvFile = new CSVFile<>(); // csv file representation
        // header file should be line number 0
        csvFile.setHeader(fileScanner.hasNext() ? new CSVStringRow(fileScanner.nextLine(), 0) : null);

        //if the first line of the header matches our pre-defined header then we should proceed, else print error msg
        if(csvFile.getHeader() instanceof  CSVStringRow && CORRECT_HEADER.rowContentEqualsIgnoreCase((CSVStringRow) csvFile.getHeader())){
            // we are going to want to loop through the entire file and hold contents in variable csvLines
            int i = 1;
            while(fileScanner.hasNext()){
                CSVStringRow currentLine = new CSVStringRow(fileScanner.nextLine(), i++);
                csvFile.getRecords().add(currentLine);
            }

            String userInputColumn;
            // handles input from command line arg
            if(args.length > 0) {
                System.out.println(args[0]);
                userInputColumn = args[0];
            // handles input from console
            }else{
                System.out.println("please enter sort column. Available choices:\n");
                System.out.println(CORRECT_HEADER + "\n");
                userInputColumn = consoleInput.nextLine();
            }

            // this logic is to figure out what column to sort by, we can use the enum sortNumber value
            // to represent index to sort by in the list
            CSVColumn sortColumn;
            try {
                 sortColumn = CSVColumn.valueOf(userInputColumn.toUpperCase());
            }catch (IllegalArgumentException iae){
                System.out.println("Could not find sort column '" + userInputColumn + "' in accepted list of values.");
                System.out.println("Exiting application...");
                return;
            }
            System.out.println("Sorting list by : " + sortColumn + "...");

            // define new comparator so we can sort the records based on the given column
            Comparator<List<String>> csvRecordComparator = new Comparator<List<String>>() {
                @Override
                public int compare(List<String> thisRecord, List<String> otherRecord) {
                    return thisRecord.get(sortColumn.getSortNumber()).compareTo(otherRecord.get(sortColumn.getSortNumber()));
                }
            };

            csvLines.sort(csvRecordComparator);
            for(List<String> record : csvLines){
                System.out.println(record);
            }

        }else{
            System.out.println("ERROR: Expected header " + CORRECT_HEADER + " does not match actual header " + fileHeader);
        }


    }
}
