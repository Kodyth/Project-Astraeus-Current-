import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * export_data.java
 * 
 * Author: Brandon Wizikowski
 * Collaborations: Geoffrey Mount
 * Date: 12/3/15
 *
 * Variable List
 *      String  filename    (name of the save file before the extension)
 *
 * Methods List
 *      void write(String) (used to write information to the savefile)
 */

public class export_data{

    public static void write() {
        try {

            //creates new file and overwrites previous files instead of amending them
        	FileChooser fileChoose = new FileChooser();
        	fileChoose.getExtensionFilters().addAll(new ExtensionFilter("csv",".csv"));
        	try( FileWriter writer = new FileWriter(fileChoose.showSaveDialog(null), false);){
        		String line="";
                
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
                    		line= line + "," + temp;
                    	}
                    	else {
                    		line=temp;
                    	}
                		
                	}
                    
                    writer.write(line+"\n");
                    line="";
                }
                writer.close();
        	}
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

