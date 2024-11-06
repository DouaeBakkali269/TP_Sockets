package EXO1;

import java.io.*;
import java.net.*;

public class Server {
    private static final String[] PROVERBES = {
            "Qui vivra verra.",
            "Mieux vaut tard que jamais.",
            "L'union fait la force.",
            "Après la pluie, le beau temps.",
    };
    public static void main(String[] args) {
        int port = 1234;  // Port d'écoute du serveur
        try {
            ServerSocket serverSocket = new ServerSocket(port); // Créer une ServerSocket qui écoute sur le port 1234
            System.out.println("Serveur en attente de connexions sur le port " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté.");
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String proverbe = PROVERBES[(int) (Math.random() * PROVERBES.length)];
                out.println(proverbe);
                clientSocket.close();
                System.out.println("Proverbe envoyé : " + proverbe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
