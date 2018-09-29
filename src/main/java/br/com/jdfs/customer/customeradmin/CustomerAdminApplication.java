package br.com.jdfs.customer.customeradmin;

import br.com.jdfs.customer.customeradmin.config.EndpointConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({ EndpointConfig.class })
public class CustomerAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerAdminApplication.class, args);
	}

	/**
	 * Creates a new RestTemplate
	 *
	 * @return RestTemplate instance
	 */
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
