package root;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;

public class Main {

	//--------------------------------------------------------------------------
	//
	//  Properties
	//
	//--------------------------------------------------------------------------

	//----------------------------------
	// Constant values 
	//----------------------------------

	//----------------------------------
	//  Getters / Setters
	//----------------------------------

	//----------------------------------
	// Private
	//----------------------------------

	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------

	//----------------------------------
	// Public
	//----------------------------------

	public static void main(String[] args) throws Exception {

		if( args.length < 1 )
			throw new Exception("Expecting single argument of string message");
		
		String message = args[0];
		
		System.out.println("Start with message: " + message);
		
		WorkflowWorker wfWorker = new WorkflowWorker( newSWFClient(), "Experimental", "TEST");
		ActivityWorker acWorker = new ActivityWorker(newSWFClient(), "Experimental", "TEST");
		
		System.out.println("Created Workers");
		
		wfWorker.addWorkflowImplementationType( DeciderWFMethods.class );
		acWorker.addActivitiesImplementation(new ActivityTestMethods());
		
		System.out.println("Added implentations");
		
		wfWorker.start();
		acWorker.start();
		
		System.out.println("Start workers");
		System.out.println("Now Sleep");
		
		Thread.sleep(10000);
		
		System.out.println("Sleep Done");
		System.out.println("Make Call");
		
		DeciderWFClientExternalFactory factory = new DeciderWFClientExternalFactoryImpl(newSWFClient(), "Experimental");
		
		factory.getClient().workflowExecute(message);
	}
	
	//----------------------------------
	// Private
	//----------------------------------
	
	private static AmazonSimpleWorkflowClient newSWFClient() {

		AmazonSimpleWorkflowClient swfClient = new AmazonSimpleWorkflowClient( (AWSCredentialsProvider) new InstanceProfileCredentialsProvider(false).getInstance() );
		
		swfClient.setRegion( Region.getRegion( Regions.EU_CENTRAL_1 ) );
		
		return swfClient;
	}

}
