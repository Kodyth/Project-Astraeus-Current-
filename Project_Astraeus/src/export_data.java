/**
 * export_data.java
 * 
 * @author Brandon Wizikowski
 * Collaborations: Geoffrey Mount
 * Date: 12/3/17
 *
 * Variable List
 *      String  filename    (name of the save file before the extension)
 *
 * Methods List
 *      void write(String) (used to write information to the savefile)
 */

import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class export_data{

    public static void write() {
        try {

            //opens a window to choose where to store the data, and what to name the file
        	FileChooser fileChoose = new FileChooser();
        	//only allows the user to save the data in the csv format
        	fileChoose.getExtensionFilters().addAll(new ExtensionFilter("csv",".csv"));
        	try( FileWriter writer = new FileWriter(fileChoose.showSaveDialog(null), false);){
        		
        		//line represents the temporary string that will be written to the line in the file
        		String line="";
                
        		//will continue the loop for the amount of data packets that are in the 
        		//DataLog array list
                for(int i=0;i<DataLog.allData.get(0).size();i++) {
                	String temp= DataLog.allData.get(0).get(i).getType();
                	if(i>=1) {
                		line= line + "," + temp;
                	}
                	else {
                		line=temp;
                	}
                	
                }
                writer.write(line+"\n");
                line="";
                for (int i=0;i<DataLog.allData.size();i++)
                {
                	for(int j=0;j<DataLog.allData.get(i).size();j++) {
                		String temp= Double.toString(DataLog.allData.get(i).get(j).getValue());
                		if(j>=1) {
                			//adds each value for the data to the current line separated by a comma
                    		line= line + "," + temp;
                    	}
                    	else {
                    		line=temp;
                    	}
                		
                	}
                    
                    writer.write(line+"\n");
                    line="";
                }
                
                //closes the file writer when the file is done being created
                writer.close();
        	}
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

