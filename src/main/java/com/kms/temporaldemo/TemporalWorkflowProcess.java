package com.kms.temporaldemo;

import com.kms.temporaldemo.common.SharedService;
import com.kms.temporaldemo.workflow.ExampleWorkflowCronImpl;
import com.kms.temporaldemo.workflow.ExampleWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import static com.kms.temporaldemo.common.SharedService.QUEUE_NAME;

public class TemporalWorkflowProcess {

  public static void main(String[] args) {
    // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
    WorkflowClient client = SharedService.getWorkflowClient();

    // Create a Worker factory that can be used to create Workers that poll specific Task Queues.
    WorkerFactory factory = WorkerFactory.newInstance(client);
    Worker worker = factory.newWorker(QUEUE_NAME);

    worker.registerWorkflowImplementationTypes(ExampleWorkflowImpl.class);
    worker.registerWorkflowImplementationTypes(ExampleWorkflowCronImpl.class);

    factory.start();
    // -------------------

  }
}
