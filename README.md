# Getting Started

### Temporal
- Start Temporal cluster using Docker: `cd temporal-docker` then `docker-compose up`.
- Default Temporal Engine Server will be run at the address `localhost:7233`.
- Go to [http://localhost:8088/](http://localhost:8088/) to monitor the Temporal Workflows and histories.

### App Structure + Services
- `ExampleWorkflow, ExampleWorkflowImpl`: the Workflow definition and its logical implementation.
- `TemporalWorkflowProcess`: the Workflow Process, will be used to run all the logic of `ExampleWorkflow`.
- `TemporalWorkerOne, TemporalWorkerTwo`: 2 Workers of `ExampleWorkflow`, will be used to run a specific action, defined by `ExampleWorkflowImpl`.
- `TemporaldemoApplication`: the SpringBoot Web Application that will be used as the Controller for the Workflow. We can *Start*, trigger *Signal*, do *Query* on the workflow


### Run the `TemporaldemoApplication`
All actions can be done by a GET request to the following URL:
- GET `http://localhost:8080/testing/start`: Start the Workflow
- GET `http://localhost:8080/testing/trigger`: Trigger the Signal to workflow for inputting 13 Greeting's name to the running workflow
- GET `http://localhost:8080/testing/get`: Send a Query to workflow and get back the current Greetings name inside the Workflow.
- GET `http://localhost:8080/testing/bye`: Trigger the Signal to end the waiting, then Complete the Workflow.

### Monitoring
- Each Services will have their own Console to display all the logging when running.
- Temporal can be monitor by the application [http://localhost:8088/](http://localhost:8088/).


That's all..!!