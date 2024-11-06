package EXO1;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serveurAdresse = "localhost";
        int port = 1234;

        try {

            Socket socket = new Socket(serveurAdresse, port);
            System.out.println("Connecté au serveur.");


            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String proverbe = in.readLine(); // TU peux vérifier si proverbe est nulle ou pas
            System.out.println("Proverbe reçu : " + proverbe);


            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
