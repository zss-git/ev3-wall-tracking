package subsumption;

import lejos.robotics.navigation.MovePilot;

public class GoForward extends Thread implements Task {
	
	MovePilot pilot;
	
	public GoForward(MovePilot pilot){
		this.pilot = pilot;
	}
	
	@Override
	public int getVal() {
		return 0;
	}

	@Override
	public void executeOutputs() {
		if(!pilot.isMoving()) {
			pilot.backward();
		}
	}

}
