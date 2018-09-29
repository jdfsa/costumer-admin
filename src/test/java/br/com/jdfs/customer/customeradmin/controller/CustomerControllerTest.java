package br.com.jdfs.customer.customeradmin.controller;

import br.com.jdfs.customer.customeradmin.model.Customer;
import br.com.jdfs.customer.customeradmin.service.CustomerService;
import br.com.jdfs.customer.customeradmin.service.IpDiscoveryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = { CustomerController.class })
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private IpDiscoveryService ipDiscoveryService;

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
        when(customerService.getCustomer()).thenReturn(customersMock);
        when(customerService.getCustomer(anyString())).thenReturn(customersMock.get(2));
        when(customerService.addCustomer(any(Customer.class), anyString())).thenReturn(customersMock.get(1));
        when(customerService.updateCustomer(anyString(), any(Customer.class))).thenReturn(customersMock.get(2));
        when(ipDiscoveryService.getIpAddress()).thenReturn("10.10.10.10");
    }

    @Test
    public void testGetCustomer() throws Exception {
        mvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[1].id", is("id-test-2")))
                .andExpect(jsonPath("$[1].name", is("name-test-2")))
                .andExpect(jsonPath("$[1].age", is(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        mvc.perform(get("/customer/{id}", "id-test-3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is("id-test-3")))
                .andExpect(jsonPath("$.name", is("name-test-3")))
                .andExpect(jsonPath("$.age", is(3)));
    }

    @Test
    public void testAddCustomer() throws Exception {
        mvc.perform(post("/customer")
                .header("Content-Type", "application/json")
                .content("{ \"name\": \"name-test-2\", \"age\": 2 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is("id-test-2")))
                .andExpect(jsonPath("$.name", is("name-test-2")))
                .andExpect(jsonPath("$.age", is(2)));
    }

    @Test
    public void testAddInvalidCustomer() throws Exception {
        mvc.perform(post("/customer")).andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        mvc.perform(put("/customer/{id}", "id-test-1")
                .header("Content-Type", "application/json")
                .content("{ \"name\": \"name-test-3\", \"age\": 3 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is("id-test-3")))
                .andExpect(jsonPath("$.name", is("name-test-3")))
                .andExpect(jsonPath("$.age", is(3)));
    }

    @Test
    public void testUpdateInvalidCustomer() throws Exception {
        mvc.perform(put("/customer/{id}", "id-test-1")).andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mvc.perform(delete("/customer/{id}", "id-test-1")).andExpect(status().isOk());
    }
}
