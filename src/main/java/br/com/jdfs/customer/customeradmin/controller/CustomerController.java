package br.com.jdfs.customer.customeradmin.controller;

import br.com.jdfs.customer.customeradmin.model.Customer;
import br.com.jdfs.customer.customeradmin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Customer controller
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Gets all customeres from the repository
     *
     * @return list of customers
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Customer>> getCustomer() {
        return ResponseEntity.ok(customerService.getCustomer());
    }

    /**
     * Gets a customer by id
     *
     * @param id the customer id to be retrieved
     * @return the customer data
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    /**
     * Register a new customer
     *
     * @param customer the customer to be registered
     * @return the registered customer data
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    /**
     * Updates a customer
     *
     * @param id the customer id to be changed
     * @param customer the new customer data
     * @return the up to date customer data
     */
    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id,
                                                   @Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    /**
     * Deletes a customer
     *
     * @param id customeer id to be deleted
     */
    @DeleteMapping(value = "/{id}")
    public void deleteCustomer(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
    }
}
