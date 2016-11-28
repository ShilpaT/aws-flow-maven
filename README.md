# aws-flow-maven
Attempt to build an AWS flow application using Maven


Has anyone been able to compile an application with Java 1.8 + AWS Flow + Maven?

I have an established Java application which has been created with Java 1.8 it uses the AWS library's and AWS flow framework. I'm looking to now automate the build of the product, I opted to use Maven. Until this point the project was exported manually within eclipse.

I have reached a point where I can build a Jar which contains our generated workflow classes ( external clients + factories ) along with what I understand to be the aspect classes ( xxxxx$1.class, xxxxx$2.class ).

The end goal is to get the weaving to happen at compile time. 

However when running the maven built jar the workflows are not working as expected. The application completly ignoring the @Asynchronous annotation and results in a not ready state. As a result it will cancel the scheduling the activity we wish to execute.

I have created a simple application with a single workflow and activity to show the issues that I'm experiencing. This version has been exported via eclipse and works, but get the error shown when building via the POM.

````
Start with message: With Comp
Created Workers
Added implentations
Nov 28, 2016 12:14:11 PM com.amazonaws.services.simpleworkflow.flow.worker.GenericWorker start
INFO: start: GenericWorkflowWorker[super=GenericWorkflowWorker[service=com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient@163e4e87, domain=Experimental, taskListToPoll=TEST, identity=3174@ip-10-0-1-141, backoffInitialInterval=100, backoffMaximumInterval=60000, backoffCoefficient=2.0], workflowDefinitionFactoryFactory=com.amazonaws.services.simpleworkflow.flow.pojo.POJOWorkflowDefinitionFactoryFactory@56de5251]
Nov 28, 2016 12:14:12 PM com.amazonaws.services.simpleworkflow.flow.worker.GenericWorker start
INFO: start: GenericActivityWorker [super=GenericActivityWorker[service=com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient@4c60d6e9, domain=Experimental, taskListToPoll=TEST, identity=3174@ip-10-0-1-141, backoffInitialInterval=100, backoffMaximumInterval=60000, backoffCoefficient=2.0], taskExecutorThreadPoolSize=100]
Start workers
Now Sleep
Sleep Done
Make Call
DECIDER 1
DECIDER 2
DECIDER DOING CATCH
java.lang.IllegalStateException: not ready
        at com.amazonaws.services.simpleworkflow.flow.core.Settable.get(Settable.java:91)
        at com.amazonaws.services.simpleworkflow.flow.core.Functor.get(Functor.java:35)
        at root.DeciderWFMethods.printMessage(DeciderWFMethods.java:79)
        at root.DeciderWFMethods.access$100(DeciderWFMethods.java:6)
        at root.DeciderWFMethods$1.doTry(DeciderWFMethods.java:54)
        at --- continuation ---.(:0)
        at root.DeciderWFMethods.workflowExecute(DeciderWFMethods.java:42)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at com.amazonaws.services.simpleworkflow.flow.pojo.POJOWorkflowDefinition.invokeMethod(POJOWorkflowDefinition.java:150)
        at com.amazonaws.services.simpleworkflow.flow.pojo.POJOWorkflowDefinition.access$1(POJOWorkflowDefinition.java:148)
        at com.amazonaws.services.simpleworkflow.flow.pojo.POJOWorkflowDefinition$1.doTry(POJOWorkflowDefinition.java:76)
        at --- continuation ---.(:0)
        at com.amazonaws.services.simpleworkflow.flow.pojo.POJOWorkflowDefinition.execute(POJOWorkflowDefinition.java:66)
        at com.amazonaws.services.simpleworkflow.flow.worker.AsyncDecider$WorkflowExecuteAsyncScope.doAsync(AsyncDecider.java:70)
DECIDER DOING FINALLY
````

Having compared the contents of the generated jar from both eclipse and maven builds there is nothing obviously different to me.

I have searched the net for something useful but only really found example for Java 1.6 / 1.7 nothing for 1.8.

It's at this point that I should mention i'm new to maven but believe its more likely to be an AspectJ configuration / AWS build tools issue rather than Maven problem. 


### Build & Run

The sample application is being run on an EC2 instance using EC2 IAM roles to execute to a Workflow domain called 'Experimental'

It accepts a string which the activity upper cases, the decider should then print the message from the activity.

To build.

```
mvn clean
```
```
mvn package
```

Then running the compiled jar 

```
java -jar Test.jar "a test message"
```

Any advice would be greatly appreciated.
