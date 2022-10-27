package com.kms.temporaldemo.worker;

import com.kms.temporaldemo.common.SharedService;
import com.kms.temporaldemo.workflow.ExampleWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;

public class WorkerStartOtherWF implements ExampleWorkflow.ExampleWorkerStartWF {

  @Override
  public String getDataFromWF() {

    WorkflowClient client = SharedService.getWorkflowClient();
    WorkflowOptions options = SharedService.getWorkflowOptionsFixedWorkflowID();

    WorkflowStub workflowStub = client.newUntypedWorkflowStub("ExampleWorkflow", options);
    workflowStub.signalWithStart("startSignal", null, new Object[] { "StartFromCron" });
    return workflowStub.query("getGreeting", String.class);
  }
}
