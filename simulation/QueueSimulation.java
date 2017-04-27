package simulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import events.Customer;
import scheduler.QueueEventScheduler;
import server.SimulationServer;
import server.Telephone;
import simulation.stopcriteria.SimulationStopCriteria;

public class QueueSimulation extends Thread implements Simulation{

	private QueueEventScheduler scheduler;
	
	private SimulationStopCriteria stopCriteria;
	private Map<Long, SimulationServer> server;
	private List<Customer> customer; // shared data
	
	private long customerCount;
	public long getServedCustomerCount(){ return this.customerCount; }
	private long eventCount;
	public long getOccuredEventCount(){ return this.eventCount; }
	private double currentTime;
	public double getSimulationTime(){ return this.currentTime; }
	
	public QueueSimulation(double meanArrival, double meanCallDuration, int seedRenewalRate, 
								int numberOfServer, List<Customer> customer, 
								SimulationStopCriteria stopCriteria){
		this.server = new HashMap<>();
		for(int i = 0; i < numberOfServer; ++i){
			Telephone t = new Telephone("Telephone" + i);
			this.server.put(t.getServerID(), t);
		}
		this.scheduler = new QueueEventScheduler(meanArrival, meanCallDuration, seedRenewalRate, TimeUnit.SECONDS, server);;
		this.stopCriteria = stopCriteria;
		this.customer = customer;
	}
	
	@Override
	public void run(){
		while(!isInterrupted()){
			Customer c = scheduler.scheduleNextCustomerEvent();
			// update stop criteria and other statistics
			this.currentTime = c.getArrivalTime();
			++this.eventCount; // count arrvial
			Customer temp;
			for(int i = customer.size() - 1; i >= 0; --i){
				temp = customer.get(i);
				// ToDo: do sth
			}
			
			// Event generated, but at this point the Event could be delayed appropriate
			// 		-> SimulationInterrupt -> SimulationInterruptHandler
			synchronized (customer) {
				customer.add(c);
			}
			// check stop criteria
			if(!this.stopCriteria.check(this)){
				this.interrupt();
			}
		}
	}
}
