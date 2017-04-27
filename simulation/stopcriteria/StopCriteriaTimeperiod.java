package simulation.stopcriteria;

import simulation.Simulation;

public class StopCriteriaTimeperiod implements SimulationStopCriteria{
	
	private double timeTillStop;
	public StopCriteriaTimeperiod(double timeTillStop) {
		this.timeTillStop = timeTillStop;
	}
	
	@Override
	public boolean check(Simulation simulation) {
		return (timeTillStop > simulation.getSimulationTime());
	}
}
