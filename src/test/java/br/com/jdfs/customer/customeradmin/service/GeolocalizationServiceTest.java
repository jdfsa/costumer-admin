package br.com.jdfs.customer.customeradmin.service;

import br.com.jdfs.customer.customeradmin.model.GeoData;
import br.com.jdfs.customer.customeradmin.model.GeoWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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
public class GeolocalizationServiceTest {

    @Mock
    @Autowired
    private GeolocalizationService geolocalizationService;

    @MockBean
    private RestTemplate restTemplate;

    @Before
    public void beforeEach() {
        when(restTemplate.getForObject(anyString(), eq(GeoWrapper.class), anyString())).thenReturn(new GeoWrapper() {
            {
                setStatus("success");
                setData(new GeoData() {
                    {
                        setLatitude("-23.28420");
                        setLongitude("-47.66800");
                    }
                });
            }
        });
    }

    @Test
    public void testGetData() {
        GeoWrapper geoWrapper = geolocalizationService.getData("0.0.0.0");
        Assert.assertEquals("-23.28420", geoWrapper.getData().getLatitude());
        Assert.assertEquals("-47.66800", geoWrapper.getData().getLongitude());
    }
}
