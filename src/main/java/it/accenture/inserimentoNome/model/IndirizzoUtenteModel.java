package it.accenture.inserimentoNome.model;


//@Embeddable //Definisce che una classe deve essere considerata parte di un @Entity proprietaria
//Classe che serve da value object per la classe IndirizzoUtenteBean
public class IndirizzoUtenteModel {

	private String via;
	private int civico;
	private String id;

	public IndirizzoUtenteModel() {}

	public IndirizzoUtenteModel(String via, int civico, String id) {
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
		StringBuilder builder = new StringBuilder();
		builder.append("via = ");
		builder.append(via);
		builder.append(", civico = ");
		builder.append(civico);
		builder.append(", con il seguente id = ");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	

}
