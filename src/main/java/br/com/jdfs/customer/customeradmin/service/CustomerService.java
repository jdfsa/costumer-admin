package br.com.jdfs.customer.customeradmin.service;

import br.com.jdfs.customer.customeradmin.model.Customer;
import br.com.jdfs.customer.customeradmin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Customer service
 */
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Gets all customers in the repository
     *
     * @return list of customers
     */
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    /**
     * Gets a customer by id
     *
     * @param id customer id
     * @return the customer
     */
    public Customer getCustomer(String id) {
        return customerRepository.findCostumerById(id);
    }

    /**
     * Registers a new customer into the repository
     *
     * @param customer the customer to be registered
     * @return the registered customer data
     */
    public Customer addCustomer(Customer customer) {
        // generates a new customer id
        customer.setId(UUID.randomUUID().toString());
        customerRepository.save(customer);
        return customer;
    }

    /**
     * Updates a customer data
     *
     * @param id customer id to be retrieved from the repository
     * @param customer customer data to be changed
     * @return the up to date customer data
     */
    public Customer updateCustomer(String id, Customer customer) {
        // reinforces the customer id
        customer.setId(id);
        customerRepository.save(customer);
        return customer;
    }

    /**
     * Deletes a customer from repository
     *
     * @param id customer id to be retrieved and deleted
     */
    public void deleteCustomer(String id) {
        Customer customer = this.getCustomer(id);
        if (customer != null) {
            customerRepository.delete(customer);
        }
    }
}
