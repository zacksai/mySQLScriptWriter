import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

/**
 * CSV Importer creates mySQL table import statements using the first line of given CSVs
 * <p>
 * Step 1: add all the file paths to a stack;
 * Step 2: iterate through each file path
 * Step 2a: read in first line of each file
 * Step 2b: create a table import statement out of each line
 * Step 2c: store it into a txt file
 * Later considerations: drop empty columns
 */
public class CSVImporter {

    public static void main(String[] args) {


        // Step 1: add all the file paths to a stack;
            // Step 1a: prompt for input using loop
            // Step 1b: Add input to stack until user exits



        // Step 2: iterate through each file path
        // Step 2a: read in first line of each file
        // Step 2b: create a table import statement out of each line
        // Step 2c: store it into a txt file
        // Later considerations: drop empty columns


        // Create console input scanner and file name stack
        Scanner keys = new Scanner(System.in);
        Stack<String> fileNames = new Stack<>();



        Scanner testFile = null;
        try {
            testFile = new Scanner(new File("/Users/zacksai/Documents/WIT/SQLCreator/WIT/src/all_downloaded_table_record_counts.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static void promptForFileName(Scanner inputScanner, Stack fileNameStack) {

        // Prompt for file name and add the file to the stack
        System.out.println("Enter file name:");


        fileNameStack.push(inputScanner.next());
    }

    /**
     * Prompts for CSV file paths, loads them into a stack, exits when instructed
     *
     * @param consoleInput reads input from scanner
     */
    public static void promptForInput(Scanner consoleInput, Stack fileNames) {

        // Create file variable:
        String nextFilePasted = "";

        // Prompt for input
        System.out.println("Paste the next CSV here, or type \"exit\" to exit.");


        // Exit when prompted
        if (consoleInput.next() != "1") {
            System.out.println("Thank you! Your SQL script is complete.");
            System.exit(0);
        }

    }
}
