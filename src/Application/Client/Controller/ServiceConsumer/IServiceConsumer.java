package Application.Client.Controller.ServiceConsumer;

import SharedLibraryMW.Message;

public interface IServiceConsumer {

	public void setHorizontal(int val);
	
	public void setVertical(int val);
	
	public void setGrip(boolean isOpen);
	
	public void handleMessage(Message msg);
}
