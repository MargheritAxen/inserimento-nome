package it.accenture.inserimentoNome.service;


import java.util.ArrayList;
import java.util.List;

import it.accenture.inserimentoNome.bean.UtenteBean;
import it.accenture.inserimentoNome.model.IndirizzoUtenteModel;
import it.accenture.inserimentoNome.model.UtenteModel;

public interface UtenteService {
	 
	UtenteModel inserisciNomeCognome(String nome, String cognome);
	
	UtenteModel creaUtente(UtenteModel utente);
	
	UtenteModel cercaNomeCognome(String nome, String cognome);

	boolean esisteUtente(UtenteModel utente);
	
	List<UtenteModel> cercaTuttiGliUtenti();
	
	IndirizzoUtenteModel creaIndirizzo(String nome, String cognome, IndirizzoUtenteModel indirizzo);

	boolean esisteIndirizzo(IndirizzoUtenteModel indirizzo);

	IndirizzoUtenteModel cercaIndirizzo(String via, int civico);
	
	IndirizzoUtenteModel cercaId(String id);
	
	void mappatura();

//	UtenteModel cercaPerChiave(String nome, String cognome);

}
