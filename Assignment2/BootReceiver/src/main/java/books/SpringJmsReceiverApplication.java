package books;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJms
public class SpringJmsReceiverApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringJmsReceiverApplication.class, args);
	}
}

