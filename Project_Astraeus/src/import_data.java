import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * OpenFile.java
 * ELO Calculator
 * Author: Brandon Wizikowski
 * Collaborations: Geoffrey Mount
 * Date 12/1/15
 *
 * Variable List
 *      File    savefile    (file to be opened)
 *      int     index       (used for placing information into the correct index in the array)
 *      String  line        (temporary value that is read in from the file)
 *
 * Method List
 *      void read()  (method to open a file chooser and read the data into the program)
 */

public class import_data {


    public static void read(){

        try{
            Stage tempstage= new Stage();
            tempstage.show();
            tempstage.setOpacity(0);
            tempstage.centerOnScreen();
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv", "*.csv"));
            chooser.setTitle("Select your Save File");
            File saveFile = chooser.showOpenDialog(tempstage);
            tempstage.close();

            //Create object of OpenFile
            FileReader inputFile = new FileReader(saveFile);

            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);

            //Variable to hold the one line data
            String line;

            // Read file line by line and print on the console
           

            line=bufferReader.readLine();
            String[] datatypes=line.split(",");
            while ((line = bufferReader.readLine()) != null)   {
                Buffer.saveBuffer(line);
            }
            //Close the buffer reader
            bufferReader.close();
        }catch(Exception e){

        }


    }
}
