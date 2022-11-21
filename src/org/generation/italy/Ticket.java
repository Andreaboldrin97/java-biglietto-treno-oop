package org.generation.italy;

import java.math.BigDecimal;

/*
 * Todo
MILESTONE 1
Consegna: creare una classe Biglietto con due attributi interi: km e età del passeggero.

Nel costruttore vanno valorizzati entrambi gli attributi, assicurandosi che abbiano valori validi (creare due metodi isValidKm e isValidEta che implementano questa logica). In caso anche solo uno non sia valido, sollevare un’eccezione.

Aggiungere tre costanti:

il costo chilometrico di 21 centesimi di €/km (BigDecimal)
lo sconto over 65 del 40 % (BigDecimal)
lo sconto minorenni del 20% (BigDecimal)
Implementare il metodo public che calcola il prezzo del biglietto comprensivo di eventuale sconto (calcolaPrezzo). Per eseguire il calcolo dello sconto aggiungere un metodo private calcolaSconto, da chiamare dentro al metodo calcolaPrezzo.

MILESTONE 2
Creare una classe Biglietteria, che contiene il metodo main in cui:

chiedere all’utente di inserire il numero di km e l’età del passeggero
con quei dati provare a creare un nuovo Biglietto (gestire eventuali eccezioni con try-catch)
stampare a video il prezzo del biglietto calcolato

 */

public class Ticket {
	
	// variabili d'istanza
	private int km;
	private int age;
	// creo le costanti 
	static final BigDecimal PRICE_PER_KM = new BigDecimal(21.00);
	static final BigDecimal DISCAUNT_OVER_65 = new BigDecimal(0.40);
	static final BigDecimal DISCAUNT_UNDER_18 = new BigDecimal(0.20);
	
	
	
	// costruttore 
	// inseriamo anche la presenza di una possibile eccezione poiche indicata nei metodi
	public Ticket(int km, int age) throws Exception{
		
		//chiamo le variabili di assegnazione ( set )
		isValidKm(km);
		isValidEta(age);
	}

	// generazioni della variabili get e set
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}


	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	// creo due metodi per verificare se gli attributi inseriti sono validi
	// inseriamo anche le possibili eccezzioni
	public void isValidKm(int km) throws Exception{
		
		if (km <= 0) {
			throw new Exception("I km inseriti non solo validi");
		}
		
		//se i km inseriti sono giusti chiama la funzione per settare il valore
		setKm(km);
	}
	
	public void isValidEta(int age) throws Exception {
		
		if (age <= 0) {
			throw new Exception("L'età inserita non è valida");
		}
		
		//se l'età inserita è giusta chiama la funzione per settare il valore
		setAge(age);
	}
	
	// metodo per calcola il prezzo del biglietto
	public void priceCalculator(){
		
		BigDecimal price = new BigDecimal(0.00);
		// per fare le operazioni con i BigDecimal dobbiamo trasformare tutti i valori in BigDecimal
		price = PRICE_PER_KM.multiply(BigDecimal.valueOf(km));
		
		//chiamo la funzione per la verifica dello sconto
		calculateDiscount(price);
		
	}
	
	//metodo per calcolare lo sconto
	private BigDecimal calculateDiscount(BigDecimal price) {
		
		// controlliamo l'età per apllicare lo sconto
		if(age < 18) {
			price = price.subtract( DISCAUNT_UNDER_18.multiply(price) );
			return price;
			
		}else if(age > 65) {
			price = price.subtract( DISCAUNT_OVER_65.multiply(price) );
			return price;
		}else {
			return price;
		}
		
	}

}
