package com.kms.temporaldemo.worker;

import com.kms.temporaldemo.workflow.ExampleWorkflow;

public class WorkerOne implements ExampleWorkflow.ExampleWorker {

  @Override
  public String process(String item) {
    return "WorkerOne 1 -> " + item;
  }
}
