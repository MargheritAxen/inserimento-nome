package it.accenture.inserimentoNome.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import it.accenture.inserimentoNome.model.IndirizzoUtenteModel;
import it.accenture.inserimentoNome.model.UtenteModel;
import it.accenture.inserimentoNome.service.UtenteService;

//Il controller richiama i metodi implementati nel Service 
@Controller
@RequestMapping("/utente")
public class UtenteController {
	
	//Richiamiamo il service mediante l'autowired -> annotation
	@Autowired
	private UtenteService service;
	
	//Sostituiamo il System.out.println tradizionale con il LOGGER -> permette di  mettere a punto un ottimo 
	//sistema di logging per tenere sotto controllo il comportamento di una applicazione in fase di sviluppo
	private static Logger LOGGER = Logger.getLogger("Operazione: ");

	// Cerchi l'utente e stampi a video nome e cognome dell'utente
	@RequestMapping(value = "/utente/nome/{nome}/cognome/{cognome}", method = RequestMethod.GET)
	public ResponseEntity<UtenteModel> getUtente(@PathVariable("nome") String nome,
			@PathVariable("cognome") String cognome) {
		UtenteModel utente = service.cercaNomeCognome(nome,cognome);
		
		if (utente == null) {
			LOGGER.info("L'utente con nome " + nome + " " + cognome + " non è stato trovato.");
			//System.out.println("L'utente con nome " + nome + " " + cognome + " non è stato trovato.");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			LOGGER.info("Buongiorno Egregio/a: " + utente.getCognome() + " " + utente.getNome());
			//System.out.println("Buongiorno Egregio: " + utente.getNome() + " " + utente.getCognome());
			return new ResponseEntity<>(utente, HttpStatus.OK);
		}
	}

	// Crea l'utente -> CREAZIONE = METODO POST -> metodo post che richiede sempre il Body -> JSON
	@RequestMapping(value = "/utente", method = RequestMethod.POST)
	public ResponseEntity<UtenteModel> createUser(@RequestBody UtenteModel u, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creazione del nuovo utente: " + u.getNome() + " " + u.getCognome());
		//System.out.println("Creazione dell'utente: " + u.getNome() + " " + u.getCognome());

		if (service.esisteUtente(u)) {
			LOGGER.info("Un utente con nome: " + u.getNome() + " " + u.getCognome() + " esiste già quindi non è possibile crearlo.");
			//System.out.println("Un utente con nome: " + u.getNome() + " esiste già");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		u = service.creaUtente(u);
		
		HttpHeaders headers = new HttpHeaders();
		// Nel path va inserito quello della GET -> che corrisponde alla ricerca
		headers.setLocation(ucBuilder.path("/utente/nome/{nome}/cognome/{cognome}").buildAndExpand(u.getNome(),u.getCognome()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	

	// Restituisci tutti gli utenti creati
	@RequestMapping(value = "/utente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UtenteModel>> listAllUsers() {
		List<UtenteModel> users  = service.cercaTuttiGliUtenti();
		LOGGER.info("Gli utenti presenti sono i seguenti: " + users.toString());
		//System.out.println("Gli utenti presenti sono i seguenti: " + users);
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	
	// Crea l'indirizzo -> CREAZIONE = METODO POST -> metodo post che richiede sempre il Body -> JSON
	@RequestMapping(value = "/utente/nome/{nome}/cognome/{cognome}/indirizzo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IndirizzoUtenteModel> createIndirizzo(@PathVariable("nome") String nome, @PathVariable("cognome") String cognome,
			@RequestBody IndirizzoUtenteModel i, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creazione dell'indirizzo: via " + i.getVia() + " " + i.getCivico() + " del Signor/a: " + nome + " " + cognome);
		//System.out.println("Creazione dell'indirizzo: via " + i.getVia() + " " + i.getCivico() + " del Signor/a: " + nome + " " + cognome);
		
		if (service.esisteIndirizzo(i)) {
			LOGGER.info("L'indirizzo inserito, via: " + i.getVia() + " " + i.getCivico() + " esiste già!");
			//System.out.println("L'indirizzo: " + i.getVia() + i.getCivico() + " esiste già");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		// quando crei l'indirizzo lo leghi all'utente
		i = service.creaIndirizzo(nome,cognome,i);
	
		HttpHeaders headers = new HttpHeaders();
		// Nel path va inserito quello della GET -> che corrisponde alla ricerca
		headers.setLocation(ucBuilder.path("/indirizzo/{id}").buildAndExpand(i.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	
//	// Funzione che lega indirizzo al nome -> NON USATA
//	@RequestMapping(value = "/utente/nome/{nome}/cognome/{cognome}/indirizzo/{indirizzo}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<IndirizzoUtenteModel> legaIndirizzoUtente(@PathVariable("nome") String nome, @PathVariable("cognome") String cognome, @PathVariable("indirizzo") String indirizzo,@RequestBody IndirizzoUtenteModel i,  UriComponentsBuilder ucBuilder) {
//		UtenteModel u = new UtenteModel();
//		i.setVia(indirizzo);
//		u.setCognome(cognome);
//		u.setNome(nome);
//		i = service.legaIndirizzoAdUtente(u,i);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/utente/nome/{nome}/cognome/{cognome}/indirizzo/{id}").buildAndExpand(i.getId(), nome, cognome).toUri());
//		return new ResponseEntity<>(headers, HttpStatus.CREATED);
//	}

	// Funzione di ricerca per id dell'indirizzo
	@RequestMapping(value = "/indirizzo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IndirizzoUtenteModel> getIndirizzo(@PathVariable("id") String id) {
		
		IndirizzoUtenteModel indirizzo = service.cercaId(id);
		if (indirizzo == null) {
			LOGGER.info("L'indirizzo cercato non corrisponde a nulla");
			//System.out.println("L'indirizzo cercato non corrisponde a nulla");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			// Stampiamo correttamente anche l'indirizzo cercato
			LOGGER.info("L'indirizzo cercato corrisponde al seguente id: " + id + ".");
			LOGGER.info("Quindi l'indirizzo è in via: " + indirizzo.getVia() + " " + indirizzo.getCivico());
			//System.out.println("L'indirizzo cercato corrisponde a: " + id + ".");
			return new ResponseEntity<>(indirizzo, HttpStatus.OK);
		}
	}
	
}