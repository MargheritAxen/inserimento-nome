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

@Service("utenteService")
public class UtenteServiceImplementation implements UtenteService {

	@Autowired
	private UsersListBean listaUtenti;

	@Autowired
	private IndirizzoListBean listaIndirizzi;

	@Autowired
	private MapperFacade map;

	@Override
	public UtenteModel inserisciNomeCognome(String nome, String cognome) {
		return null;
	}

	@Override
	public UtenteModel cercaNomeCognome(String nome, String cognome) {
		for (UtenteBean utente : listaUtenti) {
			if (utente.getNome().equalsIgnoreCase(nome) && utente.getCognome().equalsIgnoreCase(cognome)) {
				return map.map(utente, UtenteModel.class);
			}
		}
		return null;
	}

	@Override
	public UtenteModel creaUtente(UtenteModel utente) {
		// qui va mappato con il service di mappatura utenteModel verso utenteBean

		UtenteBean utenteEntity = map.map(utente, UtenteBean.class);
		utenteEntity.setId(listaUtenti.size() + 1);
		listaUtenti.add(utenteEntity);
		return map.map(utenteEntity, UtenteModel.class);
	}

	@Override
	public boolean esisteUtente(UtenteModel utente) {
		return cercaNomeCognome(utente.getNome(), utente.getCognome()) != null;
	}

	@Override
	public List<UtenteModel> cercaTuttiGliUtenti() {
		return listaUtenti.stream().map(utente -> (map.map(utente, UtenteModel.class))).collect(Collectors.toList());

	}

	@Override
	public IndirizzoUtenteModel creaIndirizzo(String nome, String cognome, IndirizzoUtenteModel indirizzo) {

		IndirizzoUtenteBean indirizzoEntity = map.map(indirizzo, IndirizzoUtenteBean.class);
		indirizzoEntity.setId(listaIndirizzi.size() + " ");
		listaIndirizzi.add(indirizzoEntity);
		UtenteModel utenteModel = new UtenteModel();
		utenteModel.setCognome(cognome);
		utenteModel.setNome(nome);
		legaIndirizzoAdUtente(utenteModel, indirizzo);
		return map.map(indirizzoEntity, IndirizzoUtenteModel.class);
	}

	@Override
	public boolean esisteIndirizzo(IndirizzoUtenteModel indirizzo) {
		return cercaIndirizzo(indirizzo.getVia(), indirizzo.getCivico()) != null;
	}

	@Override
	public IndirizzoUtenteModel cercaIndirizzo(String via, int civico) {
		for (IndirizzoUtenteBean indirizzo : listaIndirizzi) {
			if (indirizzo.getVia().equalsIgnoreCase(via) && indirizzo.getCivico() == civico) {
				return map.map(indirizzo, IndirizzoUtenteModel.class);
			}
		}
		return null;
	}

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
