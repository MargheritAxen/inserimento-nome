package it.accenture.inserimentoNome.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("it.accenture.inserimentoNome.service")
@ComponentScan("it.accenture.inserimentoNome.bean")
@ComponentScan("it.accenture.inserimentoNome.model")
public class UtenteBeanConfig {
}
