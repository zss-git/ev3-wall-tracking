package subsumption;

import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import sensors.Sensor;

public class RightHit implements Task {
	Sensor rightBump;
	MovePilot pilot;
	
	public RightHit(Sensor rightBump, MovePilot pilot) {
		this.rightBump = rightBump;
		this.pilot = pilot;
	}
	
	public int getVal() {
		if(rightBump.getVal() == 1) {
			return 1;
		}
		return 0;
	}
	
	public void executeOutputs() {
		//Forward is backward
		//pilot.forward();
		//Delay.msDelay(250);
		//pilot.stop();
		pilot.forward();
		Delay.msDelay(100);
		pilot.rotate(25);
	}
}
