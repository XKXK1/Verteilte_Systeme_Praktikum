package Middleware.MiddlewareServerside;

import Application.Server.Controller.ServiceProvider.IServiceProvider;
import SharedLibraryMW.Message;
import SharedLibraryMW.MessageHandler;
import SharedLibraryMW.UdpHandler;

public class MessageHandlerServer extends MessageHandler implements Runnable {
	private IServiceProvider _provider = null;
	private boolean _running = true;

	public MessageHandlerServer(int port, IServiceProvider provider) {
		_running = true;
		this._provider = provider;
		udpHandler = new UdpHandler(port);
	}

	public MessageHandlerServer() {
		udpHandler = new UdpHandler();
	}
	
	
	@Override
	public void run() {
		System.out.println("MessageHandler started");

		while (_running) {

			Message msg = null;
			msg = receiveMessage();
			if (msg != null) {
				_provider.handleMessage(msg);
			}

		}
	}
	
}
