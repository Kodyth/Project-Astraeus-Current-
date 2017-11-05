
import java.util.*;

import javax.xml.crypto.Data;

//import sun.java2d.d3d.D3DSurfaceData;

public class PositionPointer {
public double PositionPointerX(ArrayList<ArrayList<Data>> data){
	int locationOfLat=0;
	for(int i =0;i<(DataLog.allData.get(DataLog.allData.size()-1).size()-1);i++){
		if (DataLog.allData.get(DataLog.allData.size()-1).get(i).getType() == "LON"){
			locationOfLat=i;
		}
	}
	
	double r= DataLog.allData.get(DataLog.allData.size()-1).get(locationOfLat).getValue();
	
	double result = 10*Math.cos(r-90);
return result;
}
public double PositionPointery(ArrayList<ArrayList<Data>> data){

	int locationOfLong=0;
	for(int i =0;i<(DataLog.allData.get(DataLog.allData.size()-1).size()-1);i++){
		if (DataLog.allData.get(DataLog.allData.size()-1).get(i).getType() == "LAT"){//france
			locationOfLong=i;
		}
	}
	
	double s= DataLog.allData.get(DataLog.allData.size()-1).get(locationOfLong).getValue();
	

return (s+180);
}}
