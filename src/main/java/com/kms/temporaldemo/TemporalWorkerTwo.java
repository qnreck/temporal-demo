package com.kms.temporaldemo;

import com.kms.temporaldemo.common.SharedService;
import com.kms.temporaldemo.worker.WorkerTwo;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import static com.kms.temporaldemo.common.SharedService.QUEUE_NAME;

public class TemporalWorkerTwo {

  public static void main(String[] args) {

    // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
    WorkflowClient client = SharedService.getWorkflowClient();

    // Create a Worker factory that can be used to create Workers that poll specific Task Queues.
    WorkerFactory factory = WorkerFactory.newInstance(client);
    Worker worker = factory.newWorker(QUEUE_NAME);

    // Activities are stateless and thread safe, so a shared instance is used.
    worker.registerActivitiesImplementations(new WorkerTwo());

    factory.start();
  }
}
