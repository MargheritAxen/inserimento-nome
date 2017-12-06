package it.accenture.inserimentoNome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class InserimentoNomeApplication {

	//Main che fa partire l'applicazione
	public static void main(String[] args) {
		SpringApplication.run(InserimentoNomeApplication.class, args);
	}

}
