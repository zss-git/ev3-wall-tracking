package sensors;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class BumpSensor implements Sensor {
	
	private Port sensorPort;
	private EV3TouchSensor bumpSense;
	private SampleProvider samplep;
	
	public BumpSensor(String port) {
		sensorPort = LocalEV3.get().getPort(port);
		bumpSense = new EV3TouchSensor(sensorPort);
		samplep = bumpSense.getTouchMode();
	}
	
	public int getVal() {
		float[] sample = new float[samplep.sampleSize()];
        samplep.fetchSample(sample, 0);
        int distance = (int) sample[0];
        return distance;
	}
}
