package br.com.jdfs.customer.customeradmin.service;

import br.com.jdfs.customer.customeradmin.model.Customer;
import br.com.jdfs.customer.customeradmin.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Before
    public void beforeEach() {
        List<Customer> customersMock = Arrays.asList(
                new Customer() {
                    {
                        setId("id-test-1");
                        setName("name-test-1");
                        setAge(1);
                    }
                },
                new Customer() {
                    {
                        setId("id-test-2");
                        setName("name-test-2");
                        setAge(2);
                    }
                },
                new Customer() {
                    {
                        setId("id-test-3");
                        setName("name-test-3");
                        setAge(3);
                    }
                }
        );
        when(customerRepository.findAll()).thenReturn(customersMock);
        when(customerRepository.findCostumerById(anyString())).thenReturn(customersMock.get(2));
        // when(customerRepository.delete(any(Customer.class));
    }

    @Test
    public void testGetCustomer() {
        List<Customer> customers = customerService.getCustomer();
        Assert.assertEquals(3, customers.size());
        Assert.assertEquals("name-test-2", customers.get(1).getName());
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = customerService.getCustomer("id-test-3");
        Assert.assertEquals("id-test-3", customer.getId());
        Assert.assertEquals("name-test-3", customer.getName());
        Assert.assertEquals(Long.valueOf(3), Long.valueOf(customer.getAge()));
    }

    @Test
    public void testAddCustomer() {
        Customer customer = customerService.addCustomer(new Customer() {
            {
                setName("name-test-2");
                setAge(2);
            }
        });
        Assert.assertNotNull(customer.getId());
        Assert.assertEquals("name-test-2", customer.getName());
        Assert.assertEquals(Long.valueOf(2), Long.valueOf(customer.getAge()));
    }

    @Test
    public void testUpdateCustomer() {
        Customer newCustomer = new Customer() {
            {
                setName("name-test-3-renamed");
                setAge(33);
            }
        };
        when(customerRepository.save(any(Customer.class))).thenReturn(newCustomer);

        Customer result = customerService.updateCustomer("id-test-3", newCustomer);

        Assert.assertEquals(newCustomer.getName(), result.getName());
        Assert.assertEquals(Long.valueOf(newCustomer.getAge()), Long.valueOf(result.getAge()));
    }

    @Test
    public void testDelteCustomer() {
        customerService.deleteCustomer("id-test-1");
        verify(customerRepository).delete(any(Customer.class));
    }
}
