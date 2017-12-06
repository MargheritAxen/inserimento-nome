package it.accenture.inserimentoNome.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.accenture.inserimentoNome.bean.IndirizzoListBean;
import it.accenture.inserimentoNome.bean.IndirizzoUtenteBean;
import it.accenture.inserimentoNome.bean.UsersListBean;
import it.accenture.inserimentoNome.bean.UtenteBean;
import it.accenture.inserimentoNome.model.IndirizzoUtenteModel;
import it.accenture.inserimentoNome.model.UtenteModel;
import ma.glasnost.orika.MapperFacade;

// Implementiamo i metodi descritti nell'interfaccia che andiamo a richiamare
@Service("utenteService")
public class UtenteServiceImplementation implements UtenteService {

	// Richiamiamo la lista Utenti creata nel pacchetto bean tramite l'autowired
	@Autowired
	private UsersListBean listaUtenti;
	
	// Richiamiamo la lista Indirizzi creata nel pacchetto bean tramite l'autowired
	@Autowired
	private IndirizzoListBean listaIndirizzi;

	// Richiamiamo la MapperFacade che serve per mappare le classi creata nel pacchetto config tramite l'autowired
	@Autowired
	private MapperFacade map;

//	@Override
//	public UtenteModel inserisciNomeCognome(String nome, String cognome) {
//		return null;
//	}

	// Metodo per cercare il nome ed il cognome dell'utente
	// Nella return va inserito il mapping con il model, al fine di poter modificare il value object e non 
	// il bean e dunque lavorando su un oggetto temporaneo e non l'oggetto presente nel database
	@Override
	public UtenteModel cercaNomeCognome(String nome, String cognome) {
		for (UtenteBean utente : listaUtenti) {
			if (utente.getNome().equalsIgnoreCase(nome) && utente.getCognome().equalsIgnoreCase(cognome)) {
				return map.map(utente, UtenteModel.class);
			}
		}
		return null;
	}

	// Metodo per creare l'utente mediante l'uso del mapping e richiamando quindi la MapperFacade
	@Override
	public UtenteModel creaUtente(UtenteModel utente) {
		//Mappiamo dall'utente Bean all'utente Model richiamando la classe di mapping creata nel config
		UtenteBean utenteEntity = map.map(utente, UtenteBean.class);
		utenteEntity.setId(listaUtenti.size() + 1);
		listaUtenti.add(utenteEntity);
		return map.map(utenteEntity, UtenteModel.class);
	}

	// Controlliamo che l'utente inserito non sia già presente nel database: se è presente non viene accettato
	@Override
	public boolean esisteUtente(UtenteModel utente) {
		return cercaNomeCognome(utente.getNome(), utente.getCognome()) != null;
	}

	// Cerchiamo tutti gli utenti presenti nella lista apposita
	@Override
	public List<UtenteModel> cercaTuttiGliUtenti() {
		return listaUtenti.stream().map(utente -> (map.map(utente, UtenteModel.class))).collect(Collectors.toList());

	}

	// Metodo per creare l'indirizzo andandolo a collegare con l'utente stesso
	// Il mapping avviene come con l'utente andando a mappare le classi da BEAN a MODEL, in più va creato un Utente
	// e va richiamato il metodo legaIndirizzoAdUtente passandogli l'utente e l'indirizzo in modo da collegarli
	// correttamente nel momento in cui ci creano tramite il body -> POSTMAN
	@Override
	public IndirizzoUtenteModel creaIndirizzo(String nome, String cognome, IndirizzoUtenteModel indirizzo) {
		//Mappiamo l'indirizzo Bean all'indirizzo Model richiamando la classe di mapping creata nel config
		IndirizzoUtenteBean indirizzoEntity = map.map(indirizzo, IndirizzoUtenteBean.class);
		//Modifica ultima fatta
		indirizzoEntity.setId(String.valueOf(listaIndirizzi.size()));
		listaIndirizzi.add(indirizzoEntity);
		UtenteModel utenteModel = new UtenteModel();
		utenteModel.setCognome(cognome);
		utenteModel.setNome(nome);
		legaIndirizzoAdUtente(utenteModel, indirizzo);	// Metodo sotto -> lo richiamiamo
		return map.map(indirizzoEntity, IndirizzoUtenteModel.class);
	}

	// Controlliamo che l'indirizzo inserito non sia già presente nel database: se è presente non viene accettato
	@Override
	public boolean esisteIndirizzo(IndirizzoUtenteModel indirizzo) {
		return cercaIndirizzo(indirizzo.getVia(), indirizzo.getCivico()) != null;
	}

	// Cerchiamo gli indirizzi presenti nella lista apposita
	@Override
	public IndirizzoUtenteModel cercaIndirizzo(String via, int civico) {
		for (IndirizzoUtenteBean indirizzo : listaIndirizzi) {
			if (indirizzo.getVia().equalsIgnoreCase(via) && indirizzo.getCivico() == civico) {
				return map.map(indirizzo, IndirizzoUtenteModel.class);
			}
		}
		return null;
	}

	// Cerchiamo l'indirizzo mediante l'ID e non la via ed il civico come sopra
	@Override
	public IndirizzoUtenteModel cercaId(String id) {
		for (IndirizzoUtenteBean indirizzo : listaIndirizzi) {
			if (indirizzo.getId().equals(id)) {
				return map.map(indirizzo, IndirizzoUtenteModel.class);
			}
		}
		return null;
	}

	// @Override
	// public UtenteModel cercaPerChiave(String nome, String cognome) {
	// return listaUtenti.stream().filter(user -> (user.getNome().equals(nome) &&
	// user.getCognome().equals(cognome)))
	// .findFirst().orElse(null);
	// }

	// Metodo che serve a mappare ma NON USATO in quanto mappiamo mediante la MapperFacade
	@Override
	public void mappatura() {

		UtenteBean src = new UtenteBean();
		UtenteModel dest = map.map(src, UtenteModel.class);

		assertEquals(dest.getNome(), src.getNome());
		assertEquals(dest.getCognome(), src.getCognome());
		assertEquals(dest.getId(), src.getId());
		assertEquals(dest.getIndirizzo(), src.getIndirizzo());

		IndirizzoUtenteBean source = new IndirizzoUtenteBean();
		IndirizzoUtenteModel destination = map.map(src, IndirizzoUtenteModel.class);

		assertEquals(destination.getVia(), source.getVia());
		assertEquals(destination.getCivico(), source.getCivico());
		assertEquals(destination.getId(), source.getId());
	}
	
	// Metodo che serve per legare l'indirizzo inserito con l'utente e che viene richiamato sopra nel metodo
	// creaIndirizzo
	// In sostanza cicli le due liste e setti l'indirizzo all'interno dell'utente andando a mettere come return
	// il mapping dell'indirizzo inserito dal BEAN al MODEL della classe IndirizzoUtente
	private IndirizzoUtenteModel legaIndirizzoAdUtente(UtenteModel u, IndirizzoUtenteModel i) {
		for(UtenteBean utente : listaUtenti) {
			if(utente.getNome().equals(u.getNome()) && utente.getCognome().equals(u.getCognome())){
				for(IndirizzoUtenteBean indirizzo : listaIndirizzi){
					if(indirizzo.getVia().equals(i.getVia())){
						utente.setIndirizzo(indirizzo);
						return map.map(indirizzo, IndirizzoUtenteModel.class);
					}
				}
			}
		}
		return null;
	}

}
