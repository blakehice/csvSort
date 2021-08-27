import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // main method to run the program, first argument will be location of the file, if none give we will default to ./
    public static void main(String[] args) {

        // == start constants section ==
        final String CORRECT_HEADER = "Name,State,Motto,Mayor"; // to verify the correct file
        final String FILE_PATH = args.length > 0 ? args[0] : "./"; // to define where file is located
        final String INPUT_FILE_NAME = "input.csv";
        final String OUTPUT_FILE_NAME = "output.csv";
        // == end constants section ==


        Scanner fileScanner = new Scanner(FILE_PATH + INPUT_FILE_NAME);
        List<List<String>> csvLines = new ArrayList<>(); // using a list of lists to represent lines of the csv
        String fileHeader = fileScanner.hasNext() ? fileScanner.nextLine() : null;

        //if the first line of the header matches our pre-defined header then we should proceed, else print error msg
        if(CORRECT_HEADER.equalsIgnoreCase(fileHeader)){
            // we are going to want to loop through the entire file and hold contents in variable csvLines
            while(fileScanner.hasNext()){
                String currentLine = fileScanner.nextLine();
                // we split the list on the ',' so that we can access each field of the record. useful for sorting
                csvLines.add(List.of(currentLine.split(",")));
            }
        }else{
            System.out.println("ERROR: Expected header " + CORRECT_HEADER + " does not match actual header " + fileHeader);
        }


    }
}
