package org.generation.italy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/*
 * MILESTONE 4 (BONUS)
Nella classe Biglietteria, quando viene creato un nuovo biglietto, salvare i suoi dati in un file. Alla fine del programma, mostrare a video l’elenco dei biglietti creati.
 */

import java.time.LocalDate;
import java.util.Scanner;

public class Ticketing {

	public static void main(String[] args) {
		
		//apro lo scanner per creare un input con l'user
		Scanner sc = new Scanner(System.in);
		
		
		//pongo a l'user le domande
		System.out.print("Quanti km vorrà fare ? ");
		int km = sc.nextInt();
		
		System.out.print("Mi può dire la sua età ? ");
		int age = sc.nextInt();
		
		System.out.print("vuole un biglietto con data flessibile ? [ TRUE O FALSE ] ");
		boolean isFlexible = sc.nextBoolean();
		
		// chiudo lo scanner
		sc.close();
		
		// stampo all'interno di un FILE i risulatati del biglietto 
		// creo la classe FileWriter inzializandola a null
		FileWriter myWriter = null;
		
		
		// controllo le eccezioni
		try {
			// indico a quale file farà riferimento il path dove scriverò
			myWriter = new FileWriter ("TicketingRecords.txt", true);
					
			//chiamo il costruttore per creare un nuove oggetto Ticket
			try {
				Ticket ticket1 = new Ticket(km , age, isFlexible);
				System.out.println(ticket1);
					
				// calcolo la scadenza
				System.out.println(ticket1.calculationExpiryDate());
				// calcolo il prezzo del biglietto
				System.out.printf("%.2f",ticket1.getTicketPrice(), "\n");
				System.out.println("-----------------------");
				
				
				// meto il biglietto nel file 
				myWriter.append(ticket1.toString());
					
			} catch (Exception err){
				System.err.println(err.getMessage());
			}
					
		// se c'è un'eccezzione
		}catch (Exception err){
			// stampami il messaggio d'errore
			System.err.println(err.getMessage());
				
		// ricordiamoci di chiudere il canale aperto anche se trova un errore	
		}finally {
			// in questo caso possiamo trovare qualche eccezzione nella chiusara 
			try {
				myWriter.close();
					
			// la classe pubblica IOException  estende l' eccezione Segnala che si è verificata un'eccezione I/O di qualche tipo
			}catch (IOException err){
					
				// Il printStackTrace()metodo in Java è uno strumento utilizzato per gestire eccezioni ed errori
				err.printStackTrace();
			}
		}
//-------------------------------------------------------------------------------------
		System.out.println("*****************");
		
		// stampiamo in console i risultati salvati nel file bonus4
		File ticketingRecords = new File("./TicketingRecords.txt");
		//il recupero dei dati verrà fatto tramite lo scanner
		Scanner fileSc = null;
		// controlliamo se ci sono eccezioni nel recupero dei dati del file
		try {
			
			// apro lo scanner tramite il file
			fileSc = new Scanner(ticketingRecords);
			
			// facciamo un ciclo while perche non sappiamo le righe del file
			// con .hasNextLine() controlliamo se dopo l'iterazione c'è ancora una riga
			while(fileSc.hasNextLine()) {
				
				//assegnamo ad una variabile la stringa ottenuta dal file
				String fileLine = fileSc.nextLine();
				System.out.println(fileLine);

			}
			System.out.println("*****************");
			
		// controllo se il file non viene trovato ( Eccezzione )
		}catch(FileNotFoundException err) {
			
			//stampo l'errore
			System.err.println(err.getMessage());
		// ricordiamoci di chiudere il canale anche se trova un errore
		}finally {
			fileSc.close();
		}
		
	// FINE MANI FUNCTION
	}
	
}
