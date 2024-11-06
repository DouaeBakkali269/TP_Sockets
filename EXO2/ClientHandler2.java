package EXO2;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class ClientHandler2 implements Runnable{
    private Socket clientSocket;

    public ClientHandler2(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            String filePath = "C:\\Users\\dell\\Downloads\\index.html";   // Lire le contenu HTML à partir d'un fichier
            String corps = new String(Files.readAllBytes(Paths.get(filePath)));
            String httpResponse = "HTTP/1.0 200 OK\n\n" + corps;// Construire la réponse HTTP
            OutputStream out = clientSocket.getOutputStream(); //envoyer le réponse au client
            out.write(httpResponse.getBytes("UTF-8"));
            out.flush();

            clientSocket.close();  // Fermer la connexion
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}