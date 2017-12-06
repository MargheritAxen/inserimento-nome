package it.accenture.inserimentoNome.bean;

import java.util.ArrayList;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



// E' come un layer per l'inserimento dei dati

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UsersListBean extends ArrayList<UtenteBean> {
	
}
