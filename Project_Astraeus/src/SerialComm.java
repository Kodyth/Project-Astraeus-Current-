import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;
//test
/**
 * SerialComm.java
 * Project Astraeus
 * Author: Brandon Wizikowski
 * Collaborations: Stack Exchange for example on how RXTX works
 * Date: 10/30/17
 *
 * Description: This class is responsible for reading inputs from the serial port and passing
 * 				the inputs to other classes to separate and store the data
 */


public class SerialComm implements SerialPortEventListener {
	SerialPort serialPort;
	//Define different port names for different platforms
	
	private static String portNum = COMPORT.portNum;
	private static final String PORT_NAMES[] = { 
			 portNum
	};
	
	
	/**
	 * A BufferedReader which will be fed by a InputStreamReader 
	 * converting the bytes into characters 
	 * making the displayed results codepage independent
	 */
	private BufferedReader input;
	/** The output stream to the port */
	public static OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
	private static String commandString;

	public SerialComm(String input2) {
		portNum=input2;
	}

	public void initialize() {
		// the next line is for Raspberry Pi and 
		// gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
		System.setProperty("gnu.io.rxtx.SerialPorts", portNum);

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				if (inputLine.startsWith("V")) {
					inputLine = "";
				}
				else if(inputLine.startsWith("C")) {
					
					inputLine=input.readLine();
					commandString = inputLine;
					
					
					System.out.println(commandString);
					
				}
				else {
					//The outputs here are for debugging
					System.out.println("This is the Raw input");
					System.out.println(inputLine+"\n");

					//This is where the raw input string is sent to the buffer class to be processed.
					Buffer.saveBuffer(inputLine);

				}

			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		
	}

	public static void Run(String input)  throws Exception {
		SerialComm main = new SerialComm(input);
		
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
	}

	public static void Send(String command) {
		System.out.println("Sending "+command+"...");
		try{
			output.write(command.getBytes());
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Flushing output...");
		try{
			output.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Done!");
	}

	public static String getCommand() {
		return commandString;
	}

	public static void loadJarDll(String name) throws IOException {
	    InputStream in = SerialComm.class.getResourceAsStream(name);
	    byte[] buffer = new byte[1024];
	    int read = -1;
	    File temp = new File(new File(System.getProperty("java.io.tmpdir")), name);
	    FileOutputStream fos = new FileOutputStream(temp);

	    while((read = in.read(buffer)) != -1) {
	        fos.write(buffer, 0, read);
	    }
	    fos.close();
	    in.close();

	    System.load(temp.getAbsolutePath());
	}

}

