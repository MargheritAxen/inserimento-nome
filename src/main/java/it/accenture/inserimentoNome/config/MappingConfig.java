package it.accenture.inserimentoNome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.accenture.inserimentoNome.bean.IndirizzoUtenteBean;
import it.accenture.inserimentoNome.bean.UtenteBean;
import it.accenture.inserimentoNome.model.IndirizzoUtenteModel;
import it.accenture.inserimentoNome.model.UtenteModel;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

// Creiamo la classe relativa al mapping effettuato con Orika, mapping che viene fatto tra le classi BEAN e MODEL
// Andando quindi a creare classi MODEL che servono a lavorare su un value object senza andare ad intaccare
// il bean.
@Configuration
public class MappingConfig {

	private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	private MapperFactory mapperFactory1 = new DefaultMapperFactory.Builder().build();
	
	// il mapper facade è un metodo che istanzia una nuova istanza di una classe e mappa
	// i valori delle proprietà dell'oggetto su di essa.
	@Bean
	public MapperFacade getMapperFacade() {
		this.buildMapping(mapperFactory);
		this.secondMapping(mapperFactory1);
		return mapperFactory.getMapperFacade();
	}
	
	// Mappiamo l'utente Bean con l'utente Model
	// usando la mapper factory creata precedentemente ed il metodo classMap.().field()
	// che ci permette di mappare i campi uguali di due classi differenti -> una BEAN l'altra MODEL
	private void buildMapping(MapperFactory factory) {
		
		factory.classMap(UtenteBean.class, UtenteModel.class)
		.field("nome", "nome").field("cognome", "cognome")
		.field("id", "id").field("indirizzo", "indirizzo")
		//.byDefault() nel caso di campi con lo stesso nome
		.byDefault()
		.register();
	}
	
	// Mappiamo l'utente Bean con l'indirizzo Model
	// stesso ragionamento fatto sopra ma con classi differenti legate all'indirizzo e non all'utente
	private void secondMapping(MapperFactory factory) {
		
		factory.classMap(IndirizzoUtenteBean.class, IndirizzoUtenteModel.class)
		.field("via", "via").field("civico", "civico")
		.field("id", "id")
		.byDefault()
		.register();
	}

}
