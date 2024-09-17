package exam.trivia.starwars.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * 
 * Star Wars API Spring Configuration
 * 
 */
@Configuration
public class RestClientConfig {

	/**
	 * 
	 * Star Wars API URL from configuration file
	 * 
	 */
	@Value("${swapi.url}")
	private String swapiUrl;

	@Bean
	RestClient restClient() {
		return RestClient.builder().baseUrl(swapiUrl).build();
	}
}
