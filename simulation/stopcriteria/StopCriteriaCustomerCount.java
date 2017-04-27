package simulation.stopcriteria;

import simulation.Simulation;

public class StopCriteriaCustomerCount implements SimulationStopCriteria{
	
	private long customerTillStop;
	public StopCriteriaCustomerCount(long customerTillStop) {
		this.customerTillStop = customerTillStop;
	}
	
	
	@Override
	public boolean check(Simulation simulation) {
		return (customerTillStop > simulation.getServedCustomerCount());
	}
}
