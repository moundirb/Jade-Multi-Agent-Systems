# Simple Jade Java Agent Behavior Planning

This Java code defines a Jade agent (`agent3`) with two behaviors: a TickerBehavior and a WakerBehavior.

## TickerBehavior

- Periodically executes a task every 5 seconds for a total of 6 iterations.
- Prints a message with the tick count and agent identifier.

## WakerBehavior for Agent Termination

In this example, the termination of the agent after 30 seconds is handled by a `WakerBehavior` instead of using an `if` condition in the main execution loop.

- The `WakerBehavior` is configured to wait for 30 seconds (`30000` milliseconds).
- Upon the elapsed timeout, the `handleElapsedTimeout()` method is triggered.
- Inside this method, a completion message is printed to the console, and the agent is deleted using `myAgent.doDelete()`.

This approach provides a cleaner and more asynchronous way to handle the termination of the agent after a specific duration without the need for a continuous `if` condition check in the main execution loop.

## Output

![Output]([output.png)

