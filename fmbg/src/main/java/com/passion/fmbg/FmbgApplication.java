package com.passion.fmbg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FmbgApplication {

	public static void main(String[] args) {
		SpringApplication.run(FmbgApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder rtb) {
		return rtb.build();
	}

//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) throws IOException {
//		return args -> {
//			Boardgames bg = restTemplate.getForObject(
//					"https://api.boardgameatlas.com/api/search?ids=TAAifFP590&client_id=a5PDFkKaa5", Boardgames.class
//			);
//		};
//	}

}
