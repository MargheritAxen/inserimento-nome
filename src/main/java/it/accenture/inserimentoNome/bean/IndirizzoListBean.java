package it.accenture.inserimentoNome.bean;

import java.util.ArrayList;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//Creiamo la lista che contiene gli indirizzi degli utenti
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IndirizzoListBean extends ArrayList<IndirizzoUtenteBean> {

}
