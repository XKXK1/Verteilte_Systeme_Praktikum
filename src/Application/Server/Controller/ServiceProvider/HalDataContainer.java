package Application.Server.Controller.ServiceProvider;

import org.cads.ev3.middleware.CaDSEV3RobotHAL;
import org.cads.ev3.middleware.CaDSEV3RobotType;
import org.json.simple.JSONObject;

import Application.Server.Model.RobotHal.IHalServices;

public class HalDataContainer implements IHalServices {
	private IServiceProvider gService;
	private IServiceProvider hService;
	private IServiceProvider vService;

	public HalDataContainer(IServiceProvider gService, IServiceProvider hService, IServiceProvider vService) {
		this.gService = gService;	
		this.hService = hService;	
		this.vService = vService;
		
		CaDSEV3RobotHAL.createInstance(CaDSEV3RobotType.SIMULATION, this, this);
		
		gService.setHal();
		hService.setHal();
		vService.setHal();
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
