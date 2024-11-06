package EXO2;

import java.io.*;
import java.net.*;

public class SimpleHttpServer {
    public static void main(String[] args) throws IOException {
        int port = 8000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Serveur démarré sur le port " + port);

        // Boucle pour accepter les connexions
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connexion de " + clientSocket.getInetAddress());

            // Créer un nouveau thread pour gérer la connexion
            new Thread(new ClientHandler2(clientSocket)).start();
        }
    }
}
//    public static void main(String[] args) throws IOException {
//        int port = 8000;
//        ServerSocket serverSocket = new ServerSocket(port);
//        System.out.println("Serveur démarré sur le port " + port);
//
//        while (true) {
//            try (Socket clientSocket = serverSocket.accept()) {
//                System.out.println("Connexion de " + clientSocket.getInetAddress());

//                String httpResponse = "HTTP/1.0 200 OK\n\n" +
//                        "<HTML><TITLE>Mon serveur</TITLE>Cette page a été envoyée par mon <B>Serveur</B></HTML>";
//
//                OutputStream out = clientSocket.getOutputStream();
//                out.write(httpResponse.getBytes("UTF-8"));
//                out.flush();
//            }
//        }
//    }

// nombre de connexion !! pourquoi multiple ??