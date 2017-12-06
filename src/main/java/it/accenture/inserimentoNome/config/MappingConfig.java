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

@Configuration
public class MappingConfig {

	private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	private MapperFactory mapperFactory1 = new DefaultMapperFactory.Builder().build();
	
	@Bean
	public MapperFacade getMapperFacade() {
		this.buildMapping(mapperFactory);
		this.secondMapping(mapperFactory1);
		return mapperFactory.getMapperFacade();
	}
	
	// Mappiamo l'utente Bean con l'utente Model
	
	private void buildMapping(MapperFactory factory) {
		
		factory.classMap(UtenteBean.class, UtenteModel.class)
		.field("nome", "nome").field("cognome", "cognome")
		.field("id", "id").field("indirizzo", "indirizzo")
		//.byDefault() nel caso di campi con lo stesso nome
		.byDefault()
		.register();
	}
	
	// Mappiamo l'utente Bean con l'indirizzo Model
	
	
	private void secondMapping(MapperFactory factory) {
		
		factory.classMap(IndirizzoUtenteBean.class, IndirizzoUtenteModel.class)
		.field("via", "via").field("civico", "civico")
		.field("id", "id")
		.byDefault()
		.register();
	}

}
