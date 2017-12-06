package it.accenture.inserimentoNome.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Component Scan ci permette di prendere i pacchetti specifichi da iniettare al progetto
@Configuration
@ComponentScan("it.accenture.inserimentoNome.service")
@ComponentScan("it.accenture.inserimentoNome.bean")
@ComponentScan("it.accenture.inserimentoNome.model")
// classe di configurazione per il bean in base semplicemente ai componenti utili per lavorare
// componenti identificati dalle classi settati con l'annotazione @Component
public class UtenteBeanConfig {
}
