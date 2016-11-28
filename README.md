# aws-flow-maven
Attempt to build an AWS flow application using Maven


Has anyone been able to compile an application with Java 1.8 + AWS Flow + Maven?

I have an established Java application which has been created with Java 1.8 it uses the AWS library's and AWS flow framework. I'm looking to now automate the build of the product, I opted to use Maven. Until this point the project was exported manually with eclipse.

I have reached a point where I can build a Jar which contains our generated workflow classes along with what I understand to be the aspect classes ( xxxxx$1.class, xxxxx$2.class ).

However when running this the workflows are not working as expected. The workflow task is registering but is not calling the decider to schedule the activity's. The workflow then times out and calls the decider to call the **catch block** within the workflow.

I tried including **aspectjweaver** purely as it was included within the eclipse build. This still broke but rather than waiting for a timeout executed the WF immediately regardless of any promises.

I have created a simple application with a single workflow and activity to show the issues that i'm experiencing. 

Having compared the contents of the generated jar from both eclipse and maven builds there is nothing blindingly obvious to me.

I have searched the net for something useful but only really found example for Java 1.6 / 1.7 nothing for 1.8.

It is at this point the I should mention that I'm new to maven, but believe its more likely to be an AspectJ configuration / AWS build tools issue rather than Maven problem. 

