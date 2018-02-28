package subsumption;

import lejos.robotics.navigation.MovePilot;
import sensors.Sensor;

public class LeftHit implements Task {
	Sensor leftBump;
	MovePilot pilot;
	
	public LeftHit(Sensor leftBump, MovePilot pilot) {
		this.leftBump = leftBump;
		this.pilot = pilot;
	}
	
	public int getVal() {
		if(leftBump.getVal() == 1) {
			return 1;
		}
		return 0;
	}
	
	public void executeOutputs() {
		//Forward is backward
		//pilot.forward();
		//Delay.msDelay(250);
		//pilot.stop();
		pilot.rotate(30);
	}
}
