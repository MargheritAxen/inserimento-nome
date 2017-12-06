package it.accenture.inserimentoNome.model;

import it.accenture.inserimentoNome.model.IndirizzoUtenteModel;

public class UtenteModel {

	private String nome;
	private String cognome;
	private int id;
	private IndirizzoUtenteModel indirizzo;
	
	public UtenteModel() {
		
	}

	public UtenteModel(String nome, String cognome, int id, IndirizzoUtenteModel indirizzo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.id = id;
		this.indirizzo = indirizzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IndirizzoUtenteModel getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(IndirizzoUtenteModel indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String toString() {
		return "UtenteModel [nome=" + nome + ", cognome=" + cognome + ", id=" + id + ", indirizzo=" + indirizzo + "]";
	}

}