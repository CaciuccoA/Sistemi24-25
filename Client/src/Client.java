import java.io.*;
import java.net.*;


public class Client {
    public static void main(String[] args) {
        String indirizzoServer = "localhost";
        int porta = 12345;


        try (Socket socket = new Socket(indirizzoServer, porta);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in))) {


            System.out.println(input.readLine()); // Messaggio di benvenuto dal server


            String comando;
            while (true) {
                System.out.print("Inserisci comando > ");
                comando = tastiera.readLine();
                if (comando == null || comando.equalsIgnoreCase("exit")) break;


                output.println(comando);


                String risposta;
                while ((risposta = input.readLine()) != null && !risposta.isEmpty()) {
                    System.out.println(risposta);
                    if (!input.ready()) break;
                }
            }


        } catch (IOException e) {
            System.err.println("Errore di connessione al server: " + e.getMessage());
        }
    }
}

