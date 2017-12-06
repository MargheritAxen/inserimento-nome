package it.accenture.inserimentoNome.bean;

import java.util.ArrayList;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.accenture.inserimentoNome.model.IndirizzoUtenteModel;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class IndirizzoListBean extends ArrayList<IndirizzoUtenteBean> {

}
