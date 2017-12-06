/**
 * DataLog.java
 * Project Astraeus
 * @author Brandon Wizikowski
 * Collaborations: Stack Exchange for example on how RXTX works
 * Date: 10/30/17
 *
 * Description: This is the class that is responsible for keeping all of Data input to the program organized
 * 				and accessible to other parts of the program
 */

import java.util.*;

public  class DataLog {
	
	//This array list stores all of the array lists of data that represent data packets
	public static ArrayList<ArrayList<Data>> allData= new ArrayList<ArrayList<Data>>();
	
	//This method exists for debugging
	public static void Display() {
		System.out.println("This is all of the Data that has been recorded so far");
		int size=allData.size();
		for (int i=0;i<size;i++) {
			System.out.println("This is entry number "+i);
			for (int j=0;j<5;j++) {
				String temp=Double.toString(allData.get(i).get(j).getValue());
				System.out.println(allData.get(i).get(j).getType()+":"+temp);
			}
			System.out.println();
		}
	}
	
	//Method to locate a particular set of dat from the data log so other classes can access
	//the data stored within the array list
	public static int GetLocation(String s){
		int locationOfinfo=0;
		if(DataLog.allData.size()>0){
		for(int i =0;i<(DataLog.allData.get(DataLog.allData.size()-1).size()-1);i++){
			if (DataLog.allData.get(DataLog.allData.size()-1).get(i).getType() == s){
				locationOfinfo=i;
			}
		}
		
		}
		return locationOfinfo;
	}
}
			
			
		
