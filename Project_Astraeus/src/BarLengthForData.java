
import java.util.*;


public class BarLengthForData {
	public double DataToBarLength(String Datatype, int barLength){
		int locationOfinfo=0;
		if(DataLog.allData.size()>0){
		for(int i =0;i<(DataLog.allData.get(DataLog.allData.size()-1).size());i++){
			if (DataLog.allData.get(DataLog.allData.size()-1).get(i).getType() == Datatype){
				locationOfinfo=i;
			}
		}
		
		double rawlength = DataLog.allData.get(DataLog.allData.size()-1).get(locationOfinfo).getValue();
		return((rawlength/barLength)*100);}
		else return 0;
	}
}
