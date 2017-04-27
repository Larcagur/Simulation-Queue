package server;

public abstract class SimulationServer {
	private static long serverCounter = 0;
	private final long serverID;
	
	public SimulationServer(){
		this.serverID = serverCounter++;
	}
	
	public long getServerID(){
		return this.serverID;
	}
	
	public abstract void setNextAvailabilityTimestamp(double timestamp);
	public abstract double getNextAvailabilityTimestamp();
}
