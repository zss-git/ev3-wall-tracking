package subsumption;

import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import sensors.Sensor;

public class BothHit implements Task {
	Sensor leftBump;
	Sensor rightBump;
	MovePilot pilot;
	
	public BothHit(Sensor leftBump, Sensor rightBump, MovePilot pilot) {
		this.leftBump = leftBump;
		this.rightBump = rightBump;
		this.pilot = pilot;
	}
	
	public int getVal() {
		if(leftBump.getVal() == 1 && rightBump.getVal() == 1) {
			return 1;
		}
		return 0;
	}
	
	public void executeOutputs() {
		//Forward is backward
		pilot.forward();
		Delay.msDelay(700);
		pilot.rotate(60);
	}
}
