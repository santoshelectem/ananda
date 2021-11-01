package net.codejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Cybertech1
 *
 */
@SpringBootApplication
public class ProductManagerApplication extends SpringBootServletInitializer {

	
	public static void main(final String[] args) {
		SpringApplication.run(ProductManagerApplication.class, args);
	}

}
