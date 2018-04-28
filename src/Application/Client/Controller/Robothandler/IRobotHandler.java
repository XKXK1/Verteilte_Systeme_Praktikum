package Application.Client.Controller.Robothandler;

public interface IRobotHandler {
	
	public void setVerticalGUI(int val);
	
    public void setHorizontalGUI(int val);
    
    public void setGripGUI(boolean isOpen);
    
    public void setVerticalRobot(int val);
	
    public void setHorizontalRobot(int val);
    
    public void setGripRobot(boolean isOpen);
    
    public void addRobot(String ip);

}
