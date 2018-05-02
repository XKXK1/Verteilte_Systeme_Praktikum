package SharedLibraryMW;

public enum MessageType {
	VERTICAl, HORIZONTAL, GRIP;

	public static MessageType fromInteger(int x) {
        switch(x) {
        case 0:
            return VERTICAl;
        case 1:
            return HORIZONTAL;
        case 2:
        	return GRIP;
        }
        return null;
    }
	
	public static int toInteger(MessageType type) {
		switch(type) {
		case VERTICAl: 
			return 0;
		case HORIZONTAL: 
			return 1;
		case GRIP: 
			return 2;
		}
		return -1;
	}

}
