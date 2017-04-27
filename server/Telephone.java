package server;

public class Telephone extends SimulationServer{
	
	private String resourceName;
	private double nextAvailabilityTimestamp;
	
	public Telephone(String resourceName){
		this.resourceName = resourceName;
		this.nextAvailabilityTimestamp = 0.0;
	}
	
	public String getResourceName(){
		return this.resourceName;
	}
	
	@Override
	public void setNextAvailabilityTimestamp(double timestamp){
		this.nextAvailabilityTimestamp = timestamp;
	}
	@Override
	public double getNextAvailabilityTimestamp(){
		return this.nextAvailabilityTimestamp;
	}
}
