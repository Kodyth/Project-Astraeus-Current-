/**
 * BarLengthForData.java
 * 
 * @author Author: Geoffrey Mount
 * Collaborations: None
 * Date: 12/3/17
 * Description: This class translates data packets into usable information for bar graph objects.
 */

public class BarLengthForData {
	public double DataToBarLength(String Datatype, int barLength){
		int locationOfinfo=0;
		if(DataLog.allData.size()>0){
		for(int i =0;i<(DataLog.allData.get(DataLog.allData.size()-1).size());i++){
			if (DataLog.allData.get(DataLog.allData.size()-1).get(i).getType() == Datatype){
				locationOfinfo=i;//finds whatever datatype is being requested
			}
		}
		
		double rawlength = DataLog.allData.get(DataLog.allData.size()-1).get(locationOfinfo).getValue();
		return((rawlength/barLength)*100);}//translates the most recent data of that type into bar percentage
		else return 0;
	}
}
