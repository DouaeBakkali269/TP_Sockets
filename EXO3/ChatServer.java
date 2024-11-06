package EXO3;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static List<ClientHandler> clientHandlers = new ArrayList<>();
    private static int clientIdCounter = 1; // To give each client a unique ID

    public static void main(String[] args) {
        int port = 1234; // Replace with your preferred port

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Assign a unique ID to each client
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientIdCounter++);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }
    public static void broadcastMessage(String message, ClientHandler sender) {
        String fullMessage = "Client " + sender.getClientId() + ": " + message;

        // Send the message to all clients, including the sender
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.sendMessage(fullMessage);
        }
    }
    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }
}
