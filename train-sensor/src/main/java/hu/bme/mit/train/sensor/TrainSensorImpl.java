package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	public void getAlarmFlagState(int currentSpeedLimit) {
		if(currentSpeedLimit < 0 || currentSpeedLimit>500 || (controller.getReferenceSpeed()/2) >currentSpeedLimit) {
			user.setAlarmState(true);
		}
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		getAlarmFlagState(speedLimit);
		if (!user.getAlarmState()) {
			controller.setSpeedLimit(speedLimit);
		}
	}
}
