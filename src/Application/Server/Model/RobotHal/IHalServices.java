package Application.Server.Model.RobotHal;

import org.cads.ev3.middleware.hal.ICaDSEV3RobotFeedBackListener;
import org.cads.ev3.middleware.hal.ICaDSEV3RobotStatusListener;

public interface IHalServices  extends ICaDSEV3RobotStatusListener, ICaDSEV3RobotFeedBackListener {

}
