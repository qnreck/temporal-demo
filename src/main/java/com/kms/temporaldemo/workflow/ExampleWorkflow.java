package com.kms.temporaldemo.workflow;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ExampleWorkflow {

  @WorkflowMethod
  void startWorkflow(String name);

  @SignalMethod
  void startSignal();

  @SignalMethod
  void addGreeting(String greeting);

  @SignalMethod
  void byeBye();

  @QueryMethod
  String getGreeting();

  @ActivityInterface
  public static interface ExampleWorker {
    @ActivityMethod
    String process(String item);
  }

  @ActivityInterface
  public static interface ExampleWorkerStartWF {
    @ActivityMethod
    String getDataFromWF();
  }

}