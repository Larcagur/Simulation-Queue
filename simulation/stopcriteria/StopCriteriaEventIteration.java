package simulation.stopcriteria;

import simulation.Simulation;

public class StopCriteriaEventIteration implements SimulationStopCriteria{

	private long eventsTillStop;
	public StopCriteriaEventIteration(long eventsTillStop) {
		this.eventsTillStop = eventsTillStop;
	}
	
	@Override
	public boolean check(Simulation simulation) {
		return (eventsTillStop > simulation.getOccuredEventCount());
	}
}
