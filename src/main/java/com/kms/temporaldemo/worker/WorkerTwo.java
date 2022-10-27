package com.kms.temporaldemo.worker;

import com.kms.temporaldemo.workflow.ExampleWorkflow;

public class WorkerTwo implements ExampleWorkflow.ExampleWorker {

  @Override
  public String process(String item) {
    return "WorkerTwo 2 -> " + item;
  }
}
