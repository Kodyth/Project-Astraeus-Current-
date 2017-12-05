/**
 * Data.java
 * Project Astraeus
 * Author: Brandon Wizikowski
 * Collaborations: None
 * Date: 10/30/17
 *
 * Description: This is the class to represent the Data objects that will be used in the DataLog and other components
 */


public class Data {
	
	//Attributes
	private String type;
	private double value;
	private int order;
	
	//Constructor for a Data Piece
	Data(String input1, String input2, int inputorder){
		type=input1;
		order=inputorder;
		try {
			value=Double.parseDouble(input2);
		}
		catch(java.lang.NumberFormatException e) {
			value=0;
		}
		
	}
	//Getters
	public String getType() {return type;}
	public double getValue() {return value;}
	public String getValueS() {return Double.toString(value);}
	public int getOrder() {return order;}
	
	//Setters
	public void setType(String input) {type=input;}
	public void setValue(double input) {value=input;}
	public void setOrder(int input) {order=input;}
	
	
	
	
	
	
	
	
}