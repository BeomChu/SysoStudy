package syso.syso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SysoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysoApplication.class, args);
    }

}
