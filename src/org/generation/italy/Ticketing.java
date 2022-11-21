package org.generation.italy;

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
		
		//chiamo il costruttore per creare un nuove oggetto Ticket
		try {
			Ticket ticket1 = new Ticket(km , age);
			System.out.println(ticket1);
			
			// calcolo il prezzo del biglietto
			System.out.println(ticket1.priceCalculator());
			
		} catch (Exception err){
			System.err.println(err.getMessage());
		}
	}
	
}
