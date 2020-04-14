package br.com.douglaswordell.dwbudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DwBudgetApplication {
	
	public static final String API_V1 = "/api/v1/";

	public static void main(String[] args) {
		SpringApplication.run(DwBudgetApplication.class, args);
	}

}
