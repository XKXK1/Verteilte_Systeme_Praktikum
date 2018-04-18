package Server;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;
import org.cads.ev3.middleware.CaDSEV3RobotType;
import org.cads.ev3.middleware.hal.ICaDSEV3RobotFeedBackListener;
import org.cads.ev3.middleware.hal.ICaDSEV3RobotStatusListener;
import org.json.simple.JSONObject;

public class HalDataContainer implements ICaDSEV3RobotStatusListener, ICaDSEV3RobotFeedBackListener {
	

	public HalDataContainer() {
		CaDSEV3RobotHAL.createInstance(CaDSEV3RobotType.SIMULATION, this, this);
	}

	@Override
	public synchronized void giveFeedbackByJSonTo(JSONObject feedback) {
		System.out.println(feedback);
	}

	@Override
	public synchronized void onStatusMessage(JSONObject status) {
		System.out.println(status);
	}

}
