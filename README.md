# Sistemi24-25
1. Prerequisiti
 - Java JDK installato (versione 8 o superiore)
 - File strutture.csv presente nella stessa directory del server
 -Tre classi Java: Server.java, GestoreClient.java, Client.java

2. Report Tecnico sul Funzionamento dell'Applicazione
 L'applicazione implementa un modello client-server per consultare un file CSV che contiene informazioni su strutture ricettive. La comunicazione tra client e server avviene tramite socket TCP, con uno scambio di comandi testuali ben definiti.

3. Usa i Comandi dal Client

Nel terminale del client, puoi inserire uno dei seguenti comandi:

GET_ROW <n> : mostra la riga numero n (es. GET_ROW 2)

GET_COLUMN <n> : mostra la colonna numero n (es. GET_COLUMN 1)

SHOW_ALL : mostra tutte le righe del CSV

exit : chiude la connessione con il server

4. Chiudi il Programma
 
 Per chiudere il client, digita exit

 Per chiudere il server, premi CTRL+C nel terminale del server
