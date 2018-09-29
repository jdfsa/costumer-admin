package br.com.jdfs.customer.customeradmin.service;

import br.com.jdfs.customer.customeradmin.exception.CustomerNotFoundException;
import br.com.jdfs.customer.customeradmin.exception.LocationDataNotAvailableException;
import br.com.jdfs.customer.customeradmin.exception.WheatherDataNotAvailableException;
import br.com.jdfs.customer.customeradmin.model.*;
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
    private CustomerRepository customerRepository;

    @Autowired
    private GeolocalizationService geolocalizationService;

    @Autowired
    private WheatherService wheatherService;

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
     * @throws CustomerNotFoundException
     */
    public Customer getCustomer(String id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findCostumerById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }

        return customer;
    }

    /**
     * Registers a new customer into the repository
     *
     * @param customer the customer to be registered
     * @return the registered customer data
     */
    public Customer addCustomer(Customer customer, String ipAddress)
            throws LocationDataNotAvailableException, WheatherDataNotAvailableException {

        // generates a new customer id
        customer.setId(UUID.randomUUID().toString());

        // get the geolocalization data
        GeoWrapper geo = geolocalizationService.getData(ipAddress);
        GeoData geoData = geo.getData();

        // get the locations data
        List<LocationData> locations = wheatherService.getLocation(geoData.getLatitude(), geoData.getLongitude());
        if (locations.size() == 0) {
            throw new LocationDataNotAvailableException("No locations available");
        }

        // get the wheather data
        // obs: the first location refers to the closest location regards the ip address
        WheatherWrapper wheatherWrapper = wheatherService.getWheatherData(locations.get(0).getWoeid());
        List<WheatherData> wheather = wheatherWrapper.getConsolidatedWheater();
        if (wheather.size() == 0) {
            throw new WheatherDataNotAvailableException("No wheather information available");
        }

        // obs: the first forecast data refers to the current day
        WheatherData wheatherData = wheather.get(0);
        customer.setMinTemp(wheatherData.getMinTemp());
        customer.setMaxTemp(wheatherData.getMaxTemp());

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
    public Customer updateCustomer(String id, Customer customer) throws CustomerNotFoundException {
        // finds the saved customer data
        Customer saved = getCustomer(id);
        if (saved == null) {
            throw new CustomerNotFoundException("Customer nof tound");
        }

        // reinforces basic information
        customer.setId(saved.getId());
        customer.setMaxTemp(saved.getMaxTemp());
        customer.setMinTemp(saved.getMinTemp());

        customerRepository.save(customer);
        return customer;
    }

    /**
     * Deletes a customer from repository
     *
     * @param id customer id to be retrieved and deleted
     */
    public void deleteCustomer(String id) throws CustomerNotFoundException {
        Customer customer = this.getCustomer(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }

        customerRepository.delete(customer);
    }
}
