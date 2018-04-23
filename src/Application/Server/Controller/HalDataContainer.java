package Application.Server.Controller;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;
import org.cads.ev3.middleware.CaDSEV3RobotType;
import org.cads.ev3.middleware.hal.ICaDSEV3RobotFeedBackListener;
import org.cads.ev3.middleware.hal.ICaDSEV3RobotStatusListener;
import org.json.simple.JSONObject;

public class HalDataContainer implements ICaDSEV3RobotStatusListener, ICaDSEV3RobotFeedBackListener {
	private GripperService gService;
	private HorizontalService hService;
	private VerticalService vService;

	public HalDataContainer(GripperService gService, HorizontalService hService, VerticalService vService) {
		this.gService = gService;
		this.hService = hService;
		this.vService = vService;
		CaDSEV3RobotHAL.createInstance(CaDSEV3RobotType.SIMULATION, this, this);
	}


	@Override
	public synchronized void giveFeedbackByJSonTo(JSONObject feedback) {
		// 
	}

	@Override
	public synchronized void onStatusMessage(JSONObject status) {
		
		String state = (String) status.get("state");
		if ("gripper".equals(state)) {
			gService.update((String)status.get("value"));
		} else if("horizontal".equals(state)) {
			hService.update(((Long)status.get("percent")).intValue());
			
		} else if("vertical".equals(state)) {
			vService.update(((Long)status.get("percent")).intValue());			
		}
		
	}
}
