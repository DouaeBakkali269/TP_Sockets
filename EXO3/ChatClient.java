package EXO3;


import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader userInput;

    public void start(String serverAddress, int port) {
        try {
            clientSocket = new Socket(serverAddress, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Connected to chat server");

            // Thread to read messages from server
            new Thread(new ServerListener()).start();

            // Main thread to send user input to server
            String message;
            while ((message = userInput.readLine()) != null) {
                out.println(message);
            }

        } catch (IOException e) {
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }

    private class ServerListener implements Runnable {
        @Override
        public void run() {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.start("localhost", 1234); // Replace with your server address and port
    }
}
