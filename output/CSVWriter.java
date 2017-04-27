package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import events.Customer;

public abstract class CSVWriter {
	public static void persistCustomerDataset(List<Customer> customer, String targetFile, char separator){
		try(BufferedWriter fwriter = new BufferedWriter(new FileWriter(targetFile))){
			fwriter.write("ArrivalTime;StartOfCallTime;EndOfCallTime;UsedResource;CallDuration\r\n");
			String arrivalTime = "";
			String startOfCallTime = "";
			String endOfCallTime = "";
			String serverID = "";
			String callDuration = "";
			for(Customer c : customer){
				arrivalTime = (c.getArrivalTime() + "").replace('.', ',');
				startOfCallTime= (c.getStartOfCallTime() + "").replace('.', ',');
				endOfCallTime = (c.getEndOfCallTime() + "").replace('.', ',');
				serverID = (c.getIDOfUsedServer() + "");
				callDuration = ((c.getEndOfCallTime() - c.getStartOfCallTime()) + "").replace('.', ',');
				fwriter.write(arrivalTime + separator + startOfCallTime + separator + 
								endOfCallTime + separator + serverID + separator + callDuration + "\r\n");
			}
		}
		catch(IOException ioex){
			System.err.println(ioex.getMessage() + "\r\nStackTrace as follows:");
			System.err.println(ioex.getStackTrace());
		}
	}
}
