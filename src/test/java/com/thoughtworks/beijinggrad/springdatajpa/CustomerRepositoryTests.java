package com.thoughtworks.beijinggrad.springdatajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository repository;

    @Test
    public void should_return_the_customer_if_find_by_last_name() {
        Customer customer = new Customer("first", "last");
        entityManager.persist(customer);

        List<Customer> customersFoundByLastName = repository.findByLastName(customer.getLastName());

        assertThat(customersFoundByLastName).extracting(Customer::getLastName).containsOnly(customer.getLastName());
    }
}
