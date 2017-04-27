package scheduler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import events.Customer;
import generator.TimeGenerator;
import server.SimulationServer;

public class QueueEventScheduler{
	
	private TimeGenerator arrivalGenerator;
	private TimeGenerator callGenerator;
	
	private double lastArrivalTime;
	private double lastStartOfCallTime;
	private double lastEndOfCallTime;
	private long lastUsedServerID;
	private Map<Long, SimulationServer> server;
	
	public QueueEventScheduler(double meanArrival, double meanCallDuration, int seedRenewalRate, 
								TimeUnit timeUnit, Map<Long, SimulationServer> server){
		this.arrivalGenerator = new TimeGenerator(timeUnit, meanArrival, seedRenewalRate);
		this.callGenerator = new TimeGenerator(timeUnit, meanCallDuration, seedRenewalRate);
		this.server = server;
		this.lastArrivalTime = 0.0;
		this.lastStartOfCallTime = 0.0;
		this.lastEndOfCallTime = 0.0;
		this.lastUsedServerID = 0L;
	}
	
	public Customer scheduleNextCustomerEvent(){
		double nextArrivalIn = this.arrivalGenerator.generateNextTimespan();
		double callDuration = this.callGenerator.generateNextTimespan();
		
		if(this.lastArrivalTime > 0){
			this.lastArrivalTime += nextArrivalIn;
			this.lastStartOfCallTime = Math.max(this.lastEndOfCallTime, this.lastArrivalTime);
			this.lastEndOfCallTime = this.lastStartOfCallTime + callDuration;
		}
		else{
			this.lastArrivalTime = nextArrivalIn;
			this.lastStartOfCallTime = nextArrivalIn;
			this.lastEndOfCallTime = nextArrivalIn + callDuration;
		}
		this.lastUsedServerID = findNextAvailableServer(callDuration);
		this.server.get(lastUsedServerID).setNextAvailabilityTimestamp(lastEndOfCallTime);
		
		return new Customer(this.lastArrivalTime, this.lastStartOfCallTime, 
									this.lastEndOfCallTime, this.lastUsedServerID);
	}
	
	private long findNextAvailableServer(double callDuration){
		long nextAvailable = Long.MAX_VALUE;
		double minTimestamp = Double.MAX_VALUE;
		for(Map.Entry<Long, SimulationServer> s: server.entrySet()){
			if(s.getValue().getNextAvailabilityTimestamp() < minTimestamp){
				nextAvailable = s.getKey().longValue();
				minTimestamp = s.getValue().getNextAvailabilityTimestamp() + callDuration;
			}
		}
		server.get(nextAvailable).setNextAvailabilityTimestamp(minTimestamp);
		return nextAvailable;
	}
}



