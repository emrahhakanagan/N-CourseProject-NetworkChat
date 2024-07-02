## --- NETOLOGY HOMEWORK ---


# Network Chat Course Project

## Project Description
This project involves developing two console applications for text messaging over a network between two or more users.

- **Chat Server**: Waits for user connections and sends new messages to clients.
- **Chat Client**: Connects to the chat server to send and receive messages.

All messages are logged in the `file.log` file on both the server and clients. The log file is appended with each application launch and message sent or received. Users can exit the chat using the `exit` command.

## Server Requirements
- Set the port for client connections through a configuration file (e.g., `settings.txt`).
- Allow clients to connect to the server at any time and join the chat.
- Send new messages to clients.
- Log all messages sent through the server with the username and timestamp.

## Client Requirements
- Choose a username for participating in the chat.
- Read application settings from a configuration file (e.g., server port number).
- Connect to the server specified in the settings.
- Exit the chat using the `exit` command.
- Log all user messages to a text file. The log file should be appended with each application launch.

## Implementation Requirements
- The server must simultaneously wait for new users and process incoming messages from users.
- Use Gradle/Maven as a build tool.
- Host the code on GitHub.
- Cover the code with unit tests.

## Implementation Steps
1. **Draw Application Diagram**:
    - Create a diagram showing the interaction between the server and clients.
2. **Describe Application Architecture**:
    - Define the number of threads and their responsibilities.
    - Develop a protocol for message exchange between applications.
3. **Create GitHub Repository**:
    - Initialize the repository.
    - Upload the source code.
4. **Develop Server**:
    - Implement the server to wait for and handle connections.
5. **Conduct Server Integration Test**:
    - Test the server using tools like Telnet.
6. **Develop Client**:
    - Implement the client to connect to the server and exchange messages.
7. **Conduct Client and Server Integration Test**:
    - Test the interaction between the server and the client.
8. **Test Server with Multiple Clients**:
    - Ensure proper functioning with multiple simultaneous user connections.
9. **Write README.md**:
    - Create project documentation.
10. **Submit for Review**:
    - Complete the project and prepare it for review.

## Usage
### Server
1. Configure the server by specifying the port in the `settings.txt` file.
2. Start the server:
   ```bash
   java -jar ChatServer.jar
    ```


### Client
1. Specify the username and server port number in the settings.txt file.
2. Start the client:
   ```bash
   java -jar ChatClient.jar
    ```
3. Exit the chat using the /exit command.

### Logging
All messages are recorded in the file.log file, which is appended with each launch and each message.

### Example Commands
   ```bash
   > java -jar ChatServer.jar
   > java -jar ChatClient.jar
   ```

