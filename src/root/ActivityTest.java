package root;
import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;

@Activities(version="1.0")
@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 500,
defaultTaskStartToCloseTimeoutSeconds = 500)
public interface ActivityTest {

	public String toUpperCaseMessage( String message ) throws Exception;
}
