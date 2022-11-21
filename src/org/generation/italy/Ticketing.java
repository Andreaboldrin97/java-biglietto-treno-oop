package org.generation.italy;

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
		
		//variabile che ci riporta la data odienerna 
		LocalDate todaysDate = LocalDate.now();
		
		// chiudo lo scanner
		sc.close();
		
		// stampo all'interno di un FILE i risulatati del biglietto 
		// creo la classe FileWriter inzializandola a null
		FileWriter myWriter = null;
		
		
		// controllo le eccezzioni
		try {
			// indico a quale file farà riferimento il path dove scriverò
			myWriter = new FileWriter ("TicketingRecords.txt", true);
					
			//chiamo il costruttore per creare un nuove oggetto Ticket
			try {
				Ticket ticket1 = new Ticket(km , age, todaysDate, isFlexible);
				System.out.println(ticket1);
					
				// calcolo la scadenza
				System.out.println(ticket1.calculationExpiryDate());
				// calcolo il prezzo del biglietto
				System.out.printf("%.2f",ticket1.priceCalculator(), "\n");
				System.out.println("-----------------------");
				
				
				// meto il biglietto nel file 
				myWriter.write(ticket1.toString());
					
			} catch (Exception err){
				System.err.println(err.getMessage());
			}
					
		// se c'è un'eccezzione
		}catch (Exception err){
			// stampami il messaggio d'errore
			System.err.println(err.getMessage());
				
		// ricordiamoci di chiudere il canale aperto	
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
	// FINE MANI FUNCTION
	}
	
}
