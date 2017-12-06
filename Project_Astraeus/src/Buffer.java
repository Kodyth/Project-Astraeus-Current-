/**
 * Buffer.java
 * Project Astraeus
 * Author: Brandon Wizikowski
 * Collaborations: None
 * Date: 10/30/17
 *
 * Description: This is a temporary hold class to break down the data and then allow it to be processed
 * 				into the DataLog Class
 */

import java.util.ArrayList;

public class Buffer {

	 //defines the number of different data values in the string
	static int OrderCount=0;
	static int  numInputs=5;
	
	public static String[] dataType=new String[numInputs];
	
	public static void saveBuffer(String input) {
		final String buffer;
		buffer=input;
		
		//method to create separate data string and add the data packet
		//to the expanding array list of all data
		createData(buffer);
	}

	//This splits the data string by commas
	private static void createData(String buffer) {
		String[] splitData= buffer.split(",");
		
		//hard coded values for labels. Future versions will allow for 
		//customization of data labels
		dataType[0]= "LAT";
		dataType[1]= "LON";
		dataType[2]= "TMP";
		dataType[3]= "VOL";
		dataType[4]= "CUR";

		//Counter Definitions
		int i=0;
		int j=0;

		//creates an ArrayList of type data with the different split data types
		ArrayList<Data> dataPacket= new ArrayList<Data>();
		for (i=0;i<5;i++) {
			System.out.println(dataType[i]+":"+splitData[i]);
		}
		
		//console outputs to test the data splitting and storage
		System.out.println();
		for (j=0;j<5;j++) {
			dataPacket.add(new Data(dataType[j],splitData[j], OrderCount));
			OrderCount++;
		}
		//Adds the new data packet to the overall DataLog
		DataLog.allData.add(dataPacket);

		//Resets the counters after all of the data in the string has been processed
		//DataLog.Display();
		if(i==4) {
			i=0;
			j=0;
			
		}
			
		
	}
}
