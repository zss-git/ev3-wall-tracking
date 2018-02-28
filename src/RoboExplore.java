import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import sensors.BumpSensor;
import sensors.Sensor;
import subsumption.BothHit;
import subsumption.GoForward;
import subsumption.LeftHit;
import subsumption.LookForWall;
import subsumption.RightHit;
import subsumption.Task;

public class RoboExplore {

	private MovePilot pilot;

	//Specific tasks involving actuators
	private Sensor leftBump;
	private Sensor rightBump;

	private Task bothHit;
	private Task leftHit;
	private Task rightHit;
	private Task lookForWall;
	
	private Task goForward;
	
	public RoboExplore() {
		//Set up movement
		Wheel leftWheel = WheeledChassis.modelWheel(Motor.B, 4.0).offset(11.0);
		Wheel rightWheel = WheeledChassis.modelWheel(Motor.C, 4.0).offset(-11.0);
		Wheel wheels[] = { leftWheel, rightWheel };
		Chassis chassis = new WheeledChassis(wheels, WheeledChassis.TYPE_DIFFERENTIAL);
		
		pilot = new MovePilot(chassis);
		pilot.setLinearSpeed(20);
		
		leftBump = new BumpSensor("S4");
		rightBump = new BumpSensor("S1");
		
		bothHit = new BothHit(leftBump, rightBump, pilot);
		leftHit = new LeftHit(leftBump, pilot);
		rightHit = new RightHit(rightBump, pilot);
		lookForWall = new LookForWall(pilot);
		
		goForward = new GoForward(pilot);
	}
	
	public void mainLoop() {
		int ticksSinceLastReset = 0;
		
		while(true) {	
			// ~ 30HZ sample rate
			Delay.msDelay(33);
		
			Task curTask = null;
			String taskString = "null";
			
			//Pick a task
			if(bothHit.getVal() == 1) {	
				//Evade both
				curTask = bothHit;
				taskString = "Evading left + right";
				ticksSinceLastReset = 0;
			}
			else if(leftHit.getVal() == 1) {
				curTask = leftHit;
				taskString = "Evading left";
				ticksSinceLastReset = 0;
			}
			else if(rightHit.getVal() == 1) {
				curTask = rightHit;
				taskString = "Evading right";
				ticksSinceLastReset = 0;
			}
			else if(ticksSinceLastReset > 30) {
				curTask = lookForWall;
				taskString = "Looking for wall";
				ticksSinceLastReset = 0;
			}
			else {
				curTask = goForward;
				taskString = "Going forward";
			}
			
			//Display clear
            LCD.clearDisplay();
			LCD.drawString(taskString, 0, 4);
			
			if(curTask != null) {
				curTask.executeOutputs();
			}
			
			ticksSinceLastReset++;
		}
	}
		
	public static void main(String args[]) throws Exception {
		RoboExplore explore = new RoboExplore();
		explore.mainLoop();
	}
}
