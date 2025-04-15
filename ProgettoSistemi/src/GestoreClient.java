import java.io.*;
import java.net.*;
import java.util.List;

public class GestoreClient implements Runnable {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private List<String[]> dati;



    public GestoreClient(Socket socket, List<String[]> dati) {
        this.socket = socket;
        this.dati = dati;
    }


    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Benvenuto! Comandi disponibili: GET_ROW <n>, GET_COLUMN <n>, SHOW_ALL, exit");
            String comando;
            while ((comando = input.readLine()) != null) {
                gestisciComando(comando.trim());
            }



        } catch (IOException e) {
            System.err.println("Errore con il client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Errore nella chiusura del socket: " + e.getMessage());
            }
        }
    }



    private void gestisciComando(String comando) {
        if (comando.startsWith("GET_ROW")) {
            try {
                int numero = Integer.parseInt(comando.split(" ")[1]);
                if (numero >= 0 && numero < dati.size()) {
                    output.println(String.join(" | ", dati.get(numero)));
                } else {
                    output.println("ERROR: Riga non valida.");
                }
            } catch (Exception e) {
                output.println("ERROR: Formato comando errato. Usa GET_ROW <numero>");
            }



        } else if (comando.startsWith("GET_COLUMN")) {
            try {
                int colonna = Integer.parseInt(comando.split(" ")[1]);
                for (String[] riga : dati) {
                    if (colonna >= 0 && colonna < riga.length) {
                        output.println(riga[colonna]);
                    } else {
                        output.println("ERROR: Colonna non valida.");
                        return;
                    }
                }
            } catch (Exception e) {
                output.println("ERROR: Formato comando errato. Usa GET_COLUMN <numero>");
            }



        } else if (comando.equalsIgnoreCase("SHOW_ALL")) {
            for (String[] riga : dati) {
                output.println(String.join(" | ", riga));
            }



        } else if (comando.equalsIgnoreCase("exit")) {
            output.println("Connessione terminata.");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        } else {
            output.println("ERROR: Comando sconosciuto.");
        }
    }
}
