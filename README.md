# Jade SMA Examples

This repository contains examples and tutorials for building Simple Multi-Agent Systems (SMA) using Jade (Java Agent DEvelopment Framework).

## Installation

To get started with the Jade examples, follow these steps:

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/your-username/your-repository.git
    cd your-repository
    ```

2. **Install Jade:**
    - Download the Jade framework from [Jade website](http://jade.tilab.com/) or include the Jade libraries in your project.

3. **Set Up IntelliJ IDEA:**
    - Open the project in IntelliJ IDEA.
    - Configure your project to include the Jade libraries.

## Running the Examples

1. **Run Configuration for Individual Agents:**
    - Open the `Run/Debug Configurations` in IntelliJ IDEA.
    - Create a new `Application` configuration.
    - Set the `Main class` to `jade.Boot`.
    - Add the following program arguments:
        ```plaintext
        -container -agents example1:org.tps.tp4_InterAgentCommunication.Agent_Calcul
        ```
    - Add the Jade libraries to the classpath.
    - Save the configuration.

2. **Run the Main Container Application:**
    - Create another `Application` configuration for the main container.
    - Set the `Main class` to `jade.Boot`.
    - Add the following program arguments:
        ```plaintext
        -gui
        ```
    - Add the Jade libraries to the classpath.
    - Save the configuration.

3. **Run the Example:**
    - Execute the main container configuration first.
    - Then, select and execute the individual agent configuration.
    - Click on the Run button to execute the example.

**Note:** Ensure proper ordering and execution of configurations for a smooth run. Adjust class paths and arguments accordingly.



## Example Run Configuration

![Run Configuration](./img/run-configuration.png)

For more information on Jade, refer to the [official documentation](http://jade.tilab.com/).
