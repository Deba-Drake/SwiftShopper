package ecommersite.swiftshopper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SwiftshopperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiftshopperApplication.class, args);
	}

}
