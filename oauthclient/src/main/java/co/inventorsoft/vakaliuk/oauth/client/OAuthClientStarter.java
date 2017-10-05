package co.inventorsoft.vakaliuk.oauth.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OAuthClientStarter {

    public static void main(String[] args) {
        SpringApplication.run(OAuthClientStarter.class, args);
    }
}