import java.util.*;

//Test for Quiz -- Brandon W
//Test for Quiz -- Kody M
//Test for Quiz -- Geoffrey Mount
//Test for Quiz -- Noah Robison
//Test for Quiz -- Chloe Brown
//Noah's is Working//retest

/**
 * DataLog.java
 * Project Astraeus
 * Author: Brandon Wizikowski
 * Collaborations: Stack Exchange for example on how RXTX works
 * Date: 10/30/17
 *
 * Description: This is the class that is responsible for keeping all of Data input to the program organized
 * 				and accessible to other parts of the program
 */

public  class DataLog {
	
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
			
			
		
