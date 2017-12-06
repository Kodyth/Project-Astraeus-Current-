
import java.util.*;

import javax.xml.crypto.Data;
/**
 * PositionPointer.java
 * 
 * @author Author: Geoffrey Mount
 * Collaborations: Kody Miller
 *Date: 12/3/17
 * Description: This class translates latitude and longitude values from the tables into usable positions.
 */

public class PositionPointer {
public double PositionPointerX(){
	if(DataLog.allData.size()<=0){return 0;}
	else{
	int locationOfLat=0;
	for(int i =0;i<(DataLog.allData.get(DataLog.allData.size()-1).size()-1);i++){
		if (DataLog.allData.get(DataLog.allData.size()-1).get(i).getType() == "LAT"){
			locationOfLat=i;//this for loop finds the latitude section of the arraylist
		}
	}
	
	double r= DataLog.allData.get(DataLog.allData.size()-1).get(locationOfLat).getValue();
	r = -r;
	r = r + 90;
	double pixelY = 2.22*r+80;//this translates the data into pixel locations for the display
	
	
return pixelY;
}}
public double PositionPointerY(){
	if(DataLog.allData.size()<=0){return 0;}else{

	
	int locationOfLong=0;
	for(int i =0;i<(DataLog.allData.get(DataLog.allData.size()-1).size()-1);i++){
		if (DataLog.allData.get(DataLog.allData.size()-1).get(i).getType() == "LON"){
			locationOfLong=i;//this for loop finds the longitude section of the arraylist
		}
	}
	double pixelX = 415;
	double s= DataLog.allData.get(DataLog.allData.size()-1).get(locationOfLong).getValue();
	s = s +180;
	pixelX = 2.08*s+80;
	if(s == 360) {
		pixelX = 85;//this translates the data into pixel locations for the display
	}
	
return pixelX;
}}}
