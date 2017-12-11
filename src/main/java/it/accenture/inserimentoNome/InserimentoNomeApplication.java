package it.accenture.inserimentoNome;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class InserimentoNomeApplication {
	
//	// Proviamo a fare la parte del logger -> cambiando anche il System.out in System.err
//	private static final Logger LOGGER = Logger.getLogger(InserimentoNomeApplication.class.getName());

	//Main che fa partire l'applicazione
	public static void main(String[] args) {
		
		//Metodo per far partire l'applicazione
		SpringApplication.run(InserimentoNomeApplication.class, args);
	}

}
