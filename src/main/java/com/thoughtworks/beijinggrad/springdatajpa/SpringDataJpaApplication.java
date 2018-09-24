package com.thoughtworks.beijinggrad.springdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringDataJpaApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(CustomerRepository repository){
		return args -> {
			repository.save(new Customer("Ming", "Sun"));
			repository.save(new Customer("Ya", "Xu"));
			repository.save(new Customer("Yanping", "Liu"));
			repository.save(new Customer("Yue", "Cao"));
			repository.save(new Customer("Peixi", "Zuo"));

			log.info("Customers found with findAll()");
			log.info("------------------------------------");
			for(Customer customer : repository.findAll()){
				log.info(customer.toString());
			}
			log.info("");

			repository.findById(1L).ifPresent(
                    customer -> {
                        log.info("Customers found with findById(1L)");
                        log.info("------------------------------------");
                        log.info(customer.toString());
                        log.info("");
					}
			);


			log.info("Customers found with findByLastName('Sun')");
            log.info("------------------------------------");
            repository.findByLastName("Sun")
                    .forEach(customer ->
                            log.info(customer.toString()));
            log.info("");
        };
	}
}
