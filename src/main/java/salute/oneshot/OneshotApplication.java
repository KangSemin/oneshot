package salute.oneshot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OneshotApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneshotApplication.class, args);
	}

}
