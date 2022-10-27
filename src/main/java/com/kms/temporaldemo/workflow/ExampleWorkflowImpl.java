package com.kms.temporaldemo.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ExampleWorkflowImpl implements ExampleWorkflow {

  private static final String TERMINATE_KEY = "Bye";
  private String greeting;
  private List<String> greetings = new ArrayList<>();
  private int count = 0;


  ActivityOptions activityOptions = ActivityOptions.newBuilder()
      .setScheduleToCloseTimeout(Duration.ofMinutes(2))
      .build();
  private ExampleWorker worker = Workflow.newActivityStub(ExampleWorker.class, activityOptions);


  @Override
  public void startWorkflow(String name) {
    greeting = name;

    while (!TERMINATE_KEY.equals(greeting)) {
      greetings.add(greeting);
      count++;
      System.out.println("Received ==> " + count + " = " + greeting);

      if (count % 5 == 0) {
        System.out.println("ENOUGH ==> " + count);
        for (var item : greetings) {
          System.out.println("Processed ==> " + worker.process(item));
        }
      }

      String oldGreeting = greeting;
      Workflow.await(() -> !Objects.equals(greeting, oldGreeting));
    }
  }

  @Override
  public String getGreeting() {
    return count + " - " + String.join(",", greetings);
  }

  @Override
  public void addGreeting(String greeting) {
    this.greeting = greeting;
  }

  @Override
  public void byeBye() {
    this.greeting = TERMINATE_KEY;
  }

  @Override
  public void startSignal() {
    // Do nothing
  }

}