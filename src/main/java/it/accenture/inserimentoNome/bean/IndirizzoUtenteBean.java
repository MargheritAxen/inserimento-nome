package it.accenture.inserimentoNome.bean;

// Embeddable Definisce una classe che deve essere considerata parte di un @Entity proprietaria
//@Embeddable
// Classe che contiene le proprietà dell'indirizzo di ogni utente -> classe BEAN
public class IndirizzoUtenteBean {

	private String via;
	private int civico;
	private String id;
	
//	private UtenteBean utente;
	
	public IndirizzoUtenteBean() {
		
	}

	public IndirizzoUtenteBean(String via, int civico, String id) {
		super();
		this.via = via;
		this.civico = civico;
		this.id = id;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getCivico() {
		return civico;
	}

	public void setCivico(int civico) {
		this.civico = civico;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IndirizzoUtenteModel [via = " + via + ", civico = " + civico + ", id = " + id + "]";
	}


}
