package com.kms.temporaldemo.common;

import com.kms.temporaldemo.workflow.ExampleWorkflow;
import com.kms.temporaldemo.workflow.ExampleWorkflowCron;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/testing")
public class TestController {

  private ExampleWorkflow workflow = null;
  private WorkflowStub workflowStubFixed = null;

  @GetMapping("/start")
  public ResponseEntity<?> startExampleWorkflow() {
    WorkflowClient client = SharedService.getWorkflowClient();
    WorkflowOptions options = SharedService.getWorkflowOptions();

    workflow = client.newWorkflowStub(ExampleWorkflow.class, options);
    WorkflowClient.start(workflow::startWorkflow, "KMS");
    return ResponseEntity.ok("Started ExampleWorkflow");
  }

  @GetMapping("/trigger")
  public ResponseEntity<?> trigger() throws InterruptedException {

    for (var i = 0; i < 13; i++) {
      workflow.addGreeting("TechCon_" + i);
      Thread.sleep(100);
    }
    return ResponseEntity.ok("Trigger ExampleWorkflow");
  }

  @GetMapping("/get")
  public ResponseEntity<?> get() {
    return ResponseEntity.ok("Get ExampleWorkflow => " + workflow.getGreeting());
  }

  @GetMapping("/bye")
  public ResponseEntity<?> bye() {
    workflow.byeBye();
    return ResponseEntity.ok("ByeBye ExampleWorkflow");
  }

  //-------------

  @GetMapping("/startCron")
  public ResponseEntity<?> startExampleWorkflowCron() {
    WorkflowClient client = SharedService.getWorkflowClient();
    WorkflowOptions options = SharedService.getWorkflowOptionsForCron(
        "ExampleWorkflowCron_ID", "@every 5s");

    ExampleWorkflowCron workflow = client.newWorkflowStub(ExampleWorkflowCron.class, options);
    WorkflowClient.start(workflow::startWorkflowCron, "KMS");
    return ResponseEntity.ok("Started ExampleWorkflowCron");
  }
}
