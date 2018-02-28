package subsumption;

import lejos.robotics.navigation.MovePilot;

public class LookForWall extends Thread implements Task {
	
	MovePilot pilot;
	
	public LookForWall(MovePilot pilot){
		this.pilot = pilot;
	}
	
	@Override
	public int getVal() {
		return 0;
	}

	@Override
	public void executeOutputs() {	
		pilot.rotate(-35);
	}

}
