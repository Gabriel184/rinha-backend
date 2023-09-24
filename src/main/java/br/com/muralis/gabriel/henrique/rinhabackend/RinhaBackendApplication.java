package br.com.muralis.gabriel.henrique.rinhabackend;

import br.com.muralis.gabriel.henrique.rinhabackend.core.CoreConfiguration;
import br.com.muralis.gabriel.henrique.rinhabackend.inbound.InboundConfiguration;
import br.com.muralis.gabriel.henrique.rinhabackend.outbound.config.OutboundConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RinhaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[]{RinhaBackendApplication.class, OutboundConfiguration.class, CoreConfiguration.class, InboundConfiguration.class}, args);
	}

}
