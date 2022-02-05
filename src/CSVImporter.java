import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

/**
 * CSV Importer creates mySQL table import statements using the first line of given CSVs
 */
public class CSVImporter {

    /*
     * Step 1: add all the file paths to a stack;
     * Step 1a: prompt for input using loop
     * Step 1b: Add input to stack until user exits
     * Step 2: iterate through each file path
     * Step 2a: read in first line of each file
     * Step 2b: create a table import statement out of each line
     * Step 2c: store it into a txt file
     * Later considerations: drop empty columns
     */


    public static void main(String[] args) {






        // Step 2: iterate through each file path
        // Step 2a: read in first line of each file
        // Step 2b: create a table import statement out of each line
        // Step 2c: store it into a txt file
        // Later considerations: drop empty columns


        // Create console input scanner and file name stack
        Scanner keys = new Scanner(System.in);
        Stack<String> fileNames = new Stack<>();

        promptForInput(keys, fileNames);
        readEachFile(fileNames);


    }

    private static void readEachFile(Stack<String> fileNames) {

        // Create strings to contain line read and output script
        String lineRead = "", outputScript = "";

        // Iterate through file names
        while (!fileNames.empty()){

            // Create a fileReader and create a script string
            try {
                FileReader csvReader = new FileReader(fileNames.pop());
            } catch (FileNotFoundException e) {
                System.out.println("Error: This file isn't in the directory: " + fileNames.pop());
                e.printStackTrace();
            }
        }

    }

    /**
     * Prompts for CSV file paths, loads them into a stack, exits when instructed
     *
     * @param consoleInput reads input from scanner
     */
    public static void promptForInput(Scanner consoleInput, Stack<String> fileNames) {

        // Welcome statement
        System.out.println("Welcome to CSV Importer!");

        // Create string to store input
        String nextFilePasted = "";

        // Continue loop until prompted to exit
        while (!nextFilePasted.equals("exit")) {

            // Prompt for input and add it to the stack
            System.out.println("Paste the next CSV here, or type \"exit\" to exit.");
            nextFilePasted = consoleInput.nextLine();
            // Test message System.out.println("NextFilePasted = " + nextFilePasted);
            fileNames.push(nextFilePasted);
        }

        System.out.println("Thanks for your input. Creating your script...");

    }
}
