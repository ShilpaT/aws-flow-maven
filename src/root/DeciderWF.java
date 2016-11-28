package root;
import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.annotations.Workflow;
import com.amazonaws.services.simpleworkflow.flow.annotations.WorkflowRegistrationOptions;
import com.amazonaws.services.simpleworkflow.model.ChildPolicy;

@Workflow
@WorkflowRegistrationOptions(defaultExecutionStartToCloseTimeoutSeconds = 1000 , defaultChildPolicy = ChildPolicy.REQUEST_CANCEL)
public interface DeciderWF {

	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------

	@Execute(version = "1.0")
	public void workflowExecute( final String message ) throws Throwable;	
}
