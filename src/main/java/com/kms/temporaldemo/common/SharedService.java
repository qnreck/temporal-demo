package com.kms.temporaldemo.common;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.client.WorkflowOptions;
import io.temporal.common.RetryOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;

import java.time.Duration;

public class SharedService {

  public static final String QUEUE_NAME = "demo-queue";

  public static WorkflowClient getWorkflowClient() {
    WorkflowServiceStubs service =
        WorkflowServiceStubs.newConnectedServiceStubs(
            WorkflowServiceStubsOptions.newBuilder()
//                .setRpcTimeout(Duration.ofSeconds(10))
//                .setRpcQueryTimeout(Duration.ofSeconds(10))
//                .setEnableKeepAlive(false)
//                .setEnableHttps(false)
                .setTarget("localhost:7233")
                .build(),
            null);

    return
        WorkflowClient.newInstance(
            service,
            WorkflowClientOptions.newBuilder()
                .setNamespace("default")
                .build());
  }

  public static WorkflowOptions getWorkflowOptions() {
    return
        WorkflowOptions.newBuilder()
            .setTaskQueue(QUEUE_NAME)
            .setWorkflowExecutionTimeout(Duration.ofMinutes(10))
            .setWorkflowRunTimeout(Duration.ofMinutes(5))
            .setWorkflowTaskTimeout(Duration.ofMinutes(2))
            .setRetryOptions(
                RetryOptions.newBuilder()
                    .setMaximumAttempts(Integer.MAX_VALUE)
                    .setInitialInterval(Duration.ofSeconds(5))
                    .setBackoffCoefficient(1.5)
                    .build())
            .build();
  }

  public static WorkflowOptions getWorkflowOptionsFixedWorkflowID() {
    return
        WorkflowOptions.newBuilder()
            .setTaskQueue(QUEUE_NAME)
            .setWorkflowId("workFlowId")
            .setWorkflowExecutionTimeout(Duration.ofMinutes(10))
            .setWorkflowRunTimeout(Duration.ofMinutes(5))
            .setWorkflowTaskTimeout(Duration.ofMinutes(2))
            .setRetryOptions(
                RetryOptions.newBuilder()
                    .setMaximumAttempts(Integer.MAX_VALUE)
                    .setInitialInterval(Duration.ofSeconds(5))
                    .setBackoffCoefficient(1.5)
                    .build())
            .build();
  }

  public static WorkflowOptions getWorkflowOptionsForCron(String workFlowId, String cronSchedule) {
    return
        WorkflowOptions.newBuilder()
            .setTaskQueue(QUEUE_NAME)
            .setWorkflowId(workFlowId)
            .setWorkflowExecutionTimeout(Duration.ofMinutes(10))
            .setWorkflowRunTimeout(Duration.ofMinutes(5))
            .setWorkflowTaskTimeout(Duration.ofMinutes(2))
            .setCronSchedule(cronSchedule)
            .setRetryOptions(
                RetryOptions.newBuilder()
                    .setMaximumAttempts(Integer.MAX_VALUE)
                    .setInitialInterval(Duration.ofSeconds(5))
                    .setBackoffCoefficient(1.5)
                    .build())
            .build();
  }
}
