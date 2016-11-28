package root;
import com.amazonaws.services.simpleworkflow.flow.annotations.Asynchronous;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.core.TryCatchFinally;

public class DeciderWFMethods implements DeciderWF {

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

	private ActivityTestClient client = new ActivityTestClientImpl();
	
	//--------------------------------------------------------------------------
	//
	//  Methods
	//
	//--------------------------------------------------------------------------

	
	//----------------------------------
	// Public
	//----------------------------------

	@Override
	public void workflowExecute( final String message) throws Throwable {
		
		new TryCatchFinally() {
			
			@Override
			protected void doTry() throws Throwable {
		
				System.out.println("DECIDER 1");
				
				Promise<String> newMessage = client.toUpperCaseMessage(message);
				
				System.out.println("DECIDER 2");
				
				//Call a message print
				printMessage(newMessage);
				
				System.out.println("DECIDER 3");
			}
			
			@Override
			protected void doFinally() throws Throwable {
				System.out.println("DECIDER DOING FINALLY");
			}
			
			@Override
			protected void doCatch(Throwable e) throws Throwable {
				System.out.println("DECIDER DOING CATCH");
				e.printStackTrace();
			}
		};
	}

	//----------------------------------
	// Private
	//----------------------------------
	
	@Asynchronous
	private void printMessage( Promise<String> newMessage ) {
		
		System.out.println( "DECIDER Got Message: " + newMessage.get() );
	}

}
