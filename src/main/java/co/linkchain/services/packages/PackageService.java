package co.linkchain.services.packages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PackageService {
    public static final RestTemplate template = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(PackageService.class, args);
    }

}
