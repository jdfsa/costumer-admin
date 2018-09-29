package br.com.jdfs.customer.customeradmin.service;

import br.com.jdfs.customer.customeradmin.model.LocationData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WheatherServiceTest {

    @Mock
    @Autowired
    private WheatherService wheatherService;

    @MockBean
    private RestTemplate restTemplate;

    @Before
    public void beforeEach() {
        List<LocationData> locationMock = Arrays.asList(
                new LocationData() {
                    {
                        setWoeid(112233);
                        setLattLong("-11.11,-22.22");
                    }
                },
                new LocationData() {
                    {
                        setWoeid(445566);
                        setLattLong("-33.33,-44.44");
                    }
                }
        );

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(new ParameterizedTypeReference<List<LocationData>>() {}), anyString()))
                .thenReturn(ResponseEntity.ok(locationMock));
    }

    @Test
    public void testGetLocation() {
        List<LocationData> locationData = wheatherService.getLocation("-11.11", "-22.22");
        Assert.assertEquals(2, locationData.size());
        Assert.assertEquals(Long.valueOf(112233), Long.valueOf(locationData.get(0).getWoeid()));
    }
}
