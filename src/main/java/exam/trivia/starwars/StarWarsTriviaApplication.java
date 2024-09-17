package exam.trivia.starwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class StarWarsTriviaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarWarsTriviaApplication.class, args);
	}
}
