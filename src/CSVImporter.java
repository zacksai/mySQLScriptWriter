import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
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
        readEachFile(keys, fileNames);


    }

    private static void readEachFile(Scanner consoleInput, Stack<String> fileNames) {

        // Create strings to contain line read and output script
        String lineRead = "", secondLineRead = "", outputScript = "";

        // Iterate through each file provided
        while (!fileNames.empty()) {

            try {

                // Save file path, create bufferedReader
                String filePath = fileNames.pop();
                BufferedReader csvReader = new BufferedReader(new FileReader(new File(filePath)));

                // Read line and split into columns
                lineRead = csvReader.readLine();
                secondLineRead = csvReader.readLine();

                String[] columns = lineRead.split(",");

                // Create table name from filePath
                String tableName = filePath.substring(filePath.lastIndexOf("/") + 1,
                        filePath.lastIndexOf(".csv"));

                // Confirm table name and ask to rename
                System.out.println("Creating table: " + tableName +
                        "\n > Press 1 to rename or anything else to continue.");
                String renameChoice = consoleInput.nextLine();
                if (renameChoice.equals("1")) {
                    System.out.println("What would you like to name this table?");
                    tableName = consoleInput.nextLine().replaceAll(" ", "_");
                    System.out.println("Renamed table to " + tableName + ".\n");
                }

                // Update user, create first line of script
                System.out.println("Now loading columns...");
                outputScript = "CREATE TABLE " + tableName + "(";

                // Iterate through each column and ask for data type
                for (int i = 0; i < columns.length; i++) {
                    columns[i] = columns[i].substring(1, columns[i].length()-1);

                    // Ask user what data type and fill in accordingly
                    System.out.println("\nData type for " + columns[i] + "?" +
                            "\n1: VARCHAR(250) \n2: INT \n3: DOUBLE \n4: DATETIME" +
                            "\n Second Line:" + secondLineRead);
                    switch (consoleInput.next()) {
                        case "1":
                            outputScript += columns[i] + " VARCHAR(250) ";
                            break;
                        case "2":
                            outputScript += columns[i] + " INT ";
                            break;
                        case "3":
                            outputScript += columns[i] + " DOUBLE ";
                            break;
                        case "4":
                            outputScript += columns[i] + " DATETIME ";
                            break;
                        default:
                            System.out.println("Initializing to default: VARCHAR(250)");
                            outputScript += columns[i] + "VARCHAR(250) ";
                            break;
                    }

                    // Prompt for non-null
                    System.out.println("\nNon Null?\n1: yes \n2: no");
                    switch (consoleInput.next()) {
                        case "1":
                            outputScript += "NOT NULL, ";
                            break;
                        case "2":
                            outputScript += ", ";
                            break;
                        default:
                            System.out.println("Initializing to default: NULL OK");
                            outputScript += ", ";
                            break;
                    }

                }

                // Complete output script
                outputScript = outputScript.substring(0, outputScript.length() - 2) + ");\n\n" +
                "LOAD DATA LOCAL INFILE \'" + filePath + "\' INTO TABLE " + tableName +
                        " FIELDS TERMINATED BY \',\' ENCLOSED BY \'\"\' LINES TERMINATED BY '\\n' IGNORE 1 LINES;\n\n";

                // Write to file
                System.out.println("\nScript prepared.\n" + outputScript);
                BufferedWriter outputWriter = new BufferedWriter(new FileWriter("Script.txt"));
                outputWriter.append(outputScript);
                outputWriter.close();

            } catch (FileNotFoundException e) {
                System.out.println("Error: This file isn't in the directory: " + fileNames.pop());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error: No lines in the file: " + fileNames.pop());
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

            if (nextFilePasted != "exit") fileNames.push(nextFilePasted);
            else break;

        }
        fileNames.pop();
        System.out.println("Thanks for your input. Creating your script...\n");

    }
}
