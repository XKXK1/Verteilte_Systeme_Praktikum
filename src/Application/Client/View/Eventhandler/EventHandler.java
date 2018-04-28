package Application.Client.View.Eventhandler;
import org.cads.ev3.gui.ICaDSRobotGUIUpdater;
import org.cads.ev3.gui.swing.CaDSRobotGUISwing;

import Application.Client.Controller.Robothandler.RobotHandler;
import Application.Client.View.GUI.IIDLCaDSEV3RMI;

public class EventHandler implements IIDLCaDSEV3RMI, IEventHandler {

	private CaDSRobotGUISwing gui = null;
	private RobotHandler robotHandler = null;

	public EventHandler(String ip) {
		gui = new CaDSRobotGUISwing(this, this, this, this, this);
		gui.addService("test");
		robotHandler = new RobotHandler(this);
		robotHandler.addRobot(ip);
	}
	
	@Override
	public void setVertical(int val){
    	gui.setVerticalProgressbar(val);
    }
	
	@Override
    public void setHorizontal(int val){
    	gui.setHorizontalProgressbar(val);
    }
    
	@Override
    public void setGrip(boolean isOpen){
    	if(isOpen){
    		gui.setGripperOpen();
    	}
    	else{
    		gui.setGripperClosed();
    	}
    }

	@Override
	public void register(ICaDSRobotGUIUpdater arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(String arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public int isUltraSonicOccupied() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentVerticalPercent() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int moveVerticalToPercent(int transactionID, int percent) throws Exception {
		robotHandler.setVerticalRobot(percent);
		return 0;
	}

	@Override
	public int getCurrentHorizontalPercent() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int moveHorizontalToPercent(int transactionID, int percent) throws Exception {
		System.out.println("Current horizontal goal: " + percent);
		robotHandler.setHorizontalRobot(percent);
		return 0;
	}

	@Override
	public int stop(int transactionID) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int closeGripper(int transactionID) throws Exception {
		robotHandler.setGripRobot(false);
		return 0;
	}

	@Override
	public int isGripperClosed() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int openGripper(int transactionID) throws Exception {
		robotHandler.setGripRobot(true);
		return 0;
	}

}
