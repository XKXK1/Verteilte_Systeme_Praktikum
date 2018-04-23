package Application.Client.View;
import org.cads.ev3.gui.ICaDSRobotGUIUpdater;
import org.cads.ev3.gui.swing.CaDSRobotGUISwing;
import org.cads.ev3.rmi.consumer.ICaDSRMIConsumer;
import org.cads.ev3.rmi.generated.cadSRMIInterface.IIDLCaDSEV3RMIMoveGripper;
import org.cads.ev3.rmi.generated.cadSRMIInterface.IIDLCaDSEV3RMIMoveHorizontal;
import org.cads.ev3.rmi.generated.cadSRMIInterface.IIDLCaDSEV3RMIMoveVertical;
import org.cads.ev3.rmi.generated.cadSRMIInterface.IIDLCaDSEV3RMIUltraSonic;

import Application.Client.Controller.RobotHandler;

public class EventHandler implements IIDLCaDSEV3RMIMoveGripper, IIDLCaDSEV3RMIMoveHorizontal, IIDLCaDSEV3RMIMoveVertical, IIDLCaDSEV3RMIUltraSonic, ICaDSRMIConsumer {

	private CaDSRobotGUISwing gui = null;
	private RobotHandler robotHandler = null;

	public EventHandler(String ip) {
		gui = new CaDSRobotGUISwing(this, this, this, this, this);
		gui.addService("test");
		robotHandler = new RobotHandler(this);
		robotHandler.addRobot(ip);
	}
	
	public void setVertical(int val){
    	gui.setVerticalProgressbar(val);
    }
	
    public void setHorizontal(int val){
    	gui.setHorizontalProgressbar(val);
    }
    
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
		robotHandler.setVertical(percent);
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
		robotHandler.setHorizontal(percent);
		return 0;
	}

	@Override
	public int stop(int transactionID) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int closeGripper(int transactionID) throws Exception {
		robotHandler.setGripper(false);
		return 0;
	}

	@Override
	public int isGripperClosed() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int openGripper(int transactionID) throws Exception {
		robotHandler.setGripper(true);
		return 0;
	}

}
