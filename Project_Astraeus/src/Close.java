//Brandon Wizikowski

//This class exists simply as a flag for wether or not the 
//Serial communication should be open or closed


//This is an abstract class so that any other class may reference and choose to turn on or off the communication 
public abstract class Close {
	
	//0 represents that serial communication is possible
	//1 means that the communication will be terminated
	public static int onoff=0;
}
