package events;

public class Customer implements SimulationEvent{
	
	private static long customerCounter = 0;
	private long idOfCustomer;
	
	private double arrivalTime;
	private double startOfCallTime;
	private double endOfCallTime;
	private long idOfUsedServer;
	
	public Customer(double arrivalTime, double startOfCallTime, double endOfCallTime, long serverID){
		this.idOfCustomer = customerCounter++;
		this.arrivalTime = arrivalTime;
		this.startOfCallTime = startOfCallTime;
		this.endOfCallTime = endOfCallTime;
		this.idOfUsedServer = serverID;
	}
	
	public long getIDOfCustomer(){ return this.idOfCustomer; }
	public double getArrivalTime(){ return this.arrivalTime; }
	public double getStartOfCallTime(){ return this.startOfCallTime; }
	public double getEndOfCallTime(){ return this.endOfCallTime; }
	public long getIDOfUsedServer(){ return this.idOfUsedServer; }
}
