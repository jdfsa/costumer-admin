package br.com.jdfs.customer.customeradmin.repository;

import br.com.jdfs.customer.customeradmin.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Extended MongoDB repository
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    /**
     * Finds a customer by id
     *
     * @param id customer id
     * @return the customer retrieved from the repository
     */
    Customer findCostumerById(String id);
}
