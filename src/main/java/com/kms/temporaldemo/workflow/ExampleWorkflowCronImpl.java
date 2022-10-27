package com.kms.temporaldemo.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;

@Slf4j
public class ExampleWorkflowCronImpl implements ExampleWorkflowCron {

  ActivityOptions activityOptions = ActivityOptions.newBuilder()
      .setScheduleToCloseTimeout(Duration.ofMinutes(2))
      .build();
  private ExampleWorkflow.ExampleWorkerStartWF worker = Workflow.newActivityStub(
      ExampleWorkflow.ExampleWorkerStartWF.class, activityOptions);

  @Override
  public String startWorkflowCron(String name) {
    System.out.println("ExampleWorkflowCron Started. " + new Date());
    String result = worker.getDataFromWF();
    System.out.println("ExampleWorkflowCron Ended. " + new Date() + " => " + result);
    return "Terminated " + new Date();
  }

  @Override
  public int getCount() {
    return 0;
  }

}