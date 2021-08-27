import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    // main method to run the program
    public static void main(String[] args) {

        // == start constants section ==
        // to verify the correct file, we can dynamically obtain the columns defined in the enum class and use them to
        // check the file header, after removing the included '[', ']', and ' ' (spaces) from the array to string output
        final String CORRECT_HEADER = Arrays.toString(CSVColumn.values())
                .replace("[","").replace("]","").replace(" ","");
        final String FILE_PATH = "/home/blake/IdeaProjects/csvSort/resources/"; // to define where file is located
        final String INPUT_FILE_NAME = "input.csv";
        final String OUTPUT_FILE_NAME = "output.csv";
        // == end constants section ==

        Scanner fileScanner;
        try {
            fileScanner = new Scanner(new File(FILE_PATH + INPUT_FILE_NAME));
        }catch (FileNotFoundException fne){
            System.out.println("FATAL ERROR!\nFile: '" + INPUT_FILE_NAME + "' not found at location " + FILE_PATH);
            System.out.println("Exiting application...");
            return;
        }
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
