package com.kms.temporaldemo.workflow;

import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ExampleWorkflowCron {

  @WorkflowMethod
  String startWorkflowCron(String name);

  @QueryMethod
  int getCount();
}