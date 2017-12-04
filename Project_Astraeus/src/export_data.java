import java.io.FileWriter;
import java.io.IOException;

/**
 * export_data.java
 * 
 * Author: Brandon Wizikowski
 * Collaborations: None
 * Date: 12/3/15
 *
 * Variable List
 *      String  filename    (name of the save file before the extension)
 *
 * Methods List
 *      void write(String) (used to write information to the savefile)
 */

public class export_data{

    public static void write(String filename) {
        try {

            //creates new file and overwrites previous files instead of amending them
            FileWriter writer = new FileWriter(filename+".csv", false);
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
            //writes a line of code for every player in the player list
            for (int i=0;i<DataLog.allData.size();i++)
            {
            	for(int j=0;j<DataLog.allData.get(i).size();j++) {
            		String temp= Double.toString(DataLog.allData.get(i).get(j).getValue());
            		if(i>=1) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

