package it.accenture.inserimentoNome.service;


import java.util.List;

import it.accenture.inserimentoNome.bean.UtenteBean;
import it.accenture.inserimentoNome.model.IndirizzoUtenteModel;
import it.accenture.inserimentoNome.model.UtenteModel;

// Interfaccia dell'utente che serve ad esporre i metodi principali i quali devono essere richiamati nel controller
// al fine di poter far funzionare correttamente il progetto
public interface UtenteService {
	 
//	//metodo che permette di inserire il nome ed il cognome -> NON USATA NE IMPLEMENTATA
//	UtenteModel inserisciNomeCognome(String nome, String cognome);
	
	// Metodo per creare l'utente
	UtenteModel creaUtente(UtenteModel utente);
	
	// Metodo per cercare l'utente mediante il nome ed il cognome
	UtenteModel cercaNomeCognome(String nome, String cognome);

	// Metodo per vedere se l'utente è già presente o meno
	boolean esisteUtente(UtenteModel utente);
	
	// Lista di tutti gli utenti che serve a stamparli a video come elenco riassuntivo 
	List<UtenteModel> cercaTuttiGliUtenti();
	
	// Lista di tutti gli indirizzi corrispondenti agli utenti inseriti in base al nome, cognome e 
	// ovviamente in base anche all'indirizzo inserito
	IndirizzoUtenteModel creaIndirizzo(String nome, String cognome, IndirizzoUtenteModel indirizzo);
	
	// Controlliamo se l'indirizzo è già presente, se è presente non lo accettiamo come con l'Utente
	boolean esisteIndirizzo(IndirizzoUtenteModel indirizzo);

	// Metodo per cercare l'indirizzo mediante la via ed il civico corrispondente
	IndirizzoUtenteModel cercaIndirizzo(String via, int civico);
	
	// Metodo per cercare l'indirizzo mediante l'ID dell'utente
	IndirizzoUtenteModel cercaId(String id);
	
	// Metodo di mappatura -> NON USATO
	void mappatura();

//	UtenteModel cercaPerChiave(String nome, String cognome);

}
