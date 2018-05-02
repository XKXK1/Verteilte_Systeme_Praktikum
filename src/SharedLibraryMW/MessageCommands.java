package SharedLibraryMW;

public enum MessageCommands { 
	GET, SET, REPLY;

	public static MessageCommands fromInteger(int x) {
        switch(x) {
        case 0:
            return GET;
        case 1:
            return SET;
        case 2:
        	return REPLY;
        }
        return null;
    }
	
	public static int toInteger(MessageCommands type) {
		switch(type) {
		case GET: 
			return 0;
		case SET: 
			return 1;
		case REPLY: 
			return 2;
		}
		return -1;
	}
}
