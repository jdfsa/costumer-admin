package br.com.jdfs.customer.customeradmin.service;

import br.com.jdfs.customer.customeradmin.model.GeoData;
import br.com.jdfs.customer.customeradmin.model.GeoWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IpDiscoveryServiceTest {

    @Mock
    @Autowired
    private IpDiscoveryService ipDiscoveryService;

    @MockBean
    private RestTemplate restTemplate;

    @Before
    public void beforeEach() {
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("10.10.10.10");
    }

    @Test
    public void testGetData() {
        String ipAddress = ipDiscoveryService.getIpAddress();
        Assert.assertEquals("10.10.10.10", ipAddress);
    }
}
