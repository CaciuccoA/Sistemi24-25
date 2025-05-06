import java.io.*;
import java.net.*;
import java.util.*;


public class Server {
    private static final int PORTA = 12345;
    private static List<String[]> datiStrutture = new ArrayList<>();


    public static void main(String[] args) {
        // Nome del file aggiornato
        String nomeFileCSV = "C:\\Users\\angelo.casciuc\\Documents\\Sistemi24-25\\Server\\Regione-Emilia-Romagna---Strutture-ricettive-alberghiere-e-non-dato-geografico.csv";
        caricaCSV(nomeFileCSV);


        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Server avviato sulla porta " + PORTA);


            while (true) {
                Socket socketClient = serverSocket.accept();
                new Thread(new GestoreClient(socketClient, datiStrutture)).start();
            }


        } catch (IOException e) {
            System.err.println("Errore nel server: " + e.getMessage());
        }
    }


    // Metodo per leggere e caricare il file CSV in memoria
    private static void caricaCSV(String percorsoFile) {
        try (BufferedReader lettore = new BufferedReader(new FileReader(percorsoFile))) {
            String riga;
            while ((riga = lettore.readLine()) != null) {
                String[] campi = riga.split(";");
                datiStrutture.add(campi);
            }
            System.out.println("File CSV caricato correttamente.");
        } catch (IOException e) {
            System.err.println("Errore nella lettura del CSV: " + e.getMessage());
        }
    }
}
