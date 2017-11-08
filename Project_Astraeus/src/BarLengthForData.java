
import java.util.*;


public class BarLengthForData {
	public double DataToBarLength(String Datatype, int barLength){
		int locationOfinfo=0;
		for(int i =0;i<(DataLog.allData.get(DataLog.allData.size()-1).size()-1);i++){
			if (DataLog.allData.get(DataLog.allData.size()-1).get(i).getType() == Datatype){
				locationOfinfo=i;
			}
		}
		
		double rawlength = DataLog.allData.get(DataLog.allData.size()-1).get(locationOfinfo).getValue();
		return((rawlength/barLength)*100);
	}
}
