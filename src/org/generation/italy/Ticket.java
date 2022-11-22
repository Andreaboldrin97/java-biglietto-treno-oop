package org.generation.italy;

import java.math.BigDecimal;
import java.time.LocalDate;

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
MILESTONE 3 (BONUS)
Alla classe Biglietto aggiungere i seguenti attributi:

data: LocalDate
flessibile: boolean Entrambi gli attributi vanno valorizzati nel costruttore.
Aggiungere due costanti:

durata normale: 30 giorni (int)
durata flessibile: 90 giorni (int)
Aggiungere un metodo (calcolaDataScadenza: LocalDate) che calcola la data di scadenza del biglietto, applicando la durata normale o flessibile in base al parametro flessibile(boolean).

Nel metodo che calcola il prezzo, se il biglietto è flessibile, maggiorare il prezzo del 10%.

Modificare la classe Biglietteria in modo che, alla creazione del Biglietto, valorizzi la data con la data odierna e il parametro flessibile in base alla scelta dell’utente. Dopo aver stampato il prezzo del biglietto, stampare a video anche la data di scadenza.

 */

public class Ticket {
	
	// variabili d'istanza
	private int km;
	private int age;
	
	// bonus 3
	private LocalDate localDate;
	private boolean isFlexible;
	
	// creo le costanti 
	private static final BigDecimal PRICE_PER_KM = new BigDecimal(0.21);
	private static final BigDecimal DISCAUNT_OVER_65 = new BigDecimal(0.40);
	private static final BigDecimal DISCAUNT_UNDER_18 = new BigDecimal(0.20);
	// creo le costanti del bonus 3
	private static final int NORMAL_MATURITY = 30;
	private static final int FLEXIBLE_MATURITY = 90;
	
	
	
	// costruttore 
	// inseriamo anche la presenza di una possibile eccezione poiche indicata nei metodi
	public Ticket(int km, int age, LocalDate localDate, boolean isFlexible) throws Exception{
		
		//chiamo le variabili di assegnazione ( set )
		isValidKm(km);
		isValidEta(age);
		// bonus 3
		setLocalDate(localDate);
		setFlexible(isFlexible);
	}

	// generazioni della variabili get e set
	public int getKm() {
		return km;
	}
	private void setKm(int km) {
		this.km = km;
	}


	public int getAge() {
		return age;
	}
	private void setAge(int age) {
		this.age = age;
	}
	
	// generazioni della variabili get e set bonus 3
	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public boolean isFlexible() {
		return isFlexible;
	}

	public void setFlexible(boolean isFlexible) {
		this.isFlexible = isFlexible;
	}

	// creo due metodi per verificare se gli attributi inseriti sono validi
	// inseriamo anche le possibili eccezioni
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
	public BigDecimal getTicketPrice(){
		
		BigDecimal price = new BigDecimal(0.00);
		// per fare le operazioni con i BigDecimal dobbiamo trasformare tutti i valori in BigDecimal
		price = PRICE_PER_KM.multiply(BigDecimal.valueOf(km));
		
		//chiamo la funzione per la verifica dello sconto
		return calculateDiscount(price);
		
	}
	
	//metodo per calcolare lo sconto
	private BigDecimal calculateDiscount(BigDecimal price) {
		
		// controlliamo l'età per apllicare lo sconto
		if(age < 18) {
			
			price = price.subtract( DISCAUNT_UNDER_18.multiply(price) );
			// chiamo la funzione per vedere se c'è n suplemento del prezzo
			return surcharge(price);
		}else if(age > 65) {
			price = price.subtract( DISCAUNT_OVER_65.multiply(price) );
			// chiamo la funzione per vedere se c'è n suplemento del prezzo
			return surcharge(price);
		}else {
			// chiamo la funzione per vedere se c'è n suplemento del prezzo
			return surcharge(price);
		}
		
	}
	
	// creo un metodo per verificare se bidogna applicare un supplemento al prezzo
	private BigDecimal surcharge(BigDecimal price) {
		// condizione per vedere se bisogna applicare una maggioranza in base alla scadenza del biglietto
		if(isFlexible) {
			return price = price.add( price.multiply( BigDecimal.valueOf(0.10) ) );
		}
			return price;
	}
	
	
	//metodo per il calcolo della scadenza
	public LocalDate calculationExpiryDate() {
		
		// verifichiamo la tipologia di scadenza del biglietto
		if(isFlexible) {
			return localDate.plusDays(FLEXIBLE_MATURITY);
		}else {
			return localDate.plusDays(NORMAL_MATURITY);
		}
	}
	
	//metodo toString per la visuallizazione dell'oggetto
	@Override
	public String toString() {
		
		return "KM da percorrere : " + getKm()
				+ "\nEtà del passeggiero : " + getAge()
				+ "\nIl data scadenza : " + calculationExpiryDate()
				+ "\nPrezzo : " + String.format("%.2f", getTicketPrice()) + "\n--------------\n";
	}

}
