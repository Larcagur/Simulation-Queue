package simulation.stopcriteria;

import simulation.Simulation;

public class StopCriteriaNone implements SimulationStopCriteria{
	
	@Override
	public boolean check(Simulation simulation) {
		return true; 
	}
}
