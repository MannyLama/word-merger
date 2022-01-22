package dev.manfred.wordMerger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WordMergerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordMergerApplication.class, args);
	}

}
