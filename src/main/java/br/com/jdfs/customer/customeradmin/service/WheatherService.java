package br.com.jdfs.customer.customeradmin.service;

import br.com.jdfs.customer.customeradmin.config.EndpointConfig;
import br.com.jdfs.customer.customeradmin.model.LocationData;
import br.com.jdfs.customer.customeradmin.model.WheatherWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Wheather service
 */
@Service
public class WheatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EndpointConfig endpointConfig;

    /**
     * Retrieves the wheather location data
     *
     * @param latitude
     * @param longitude
     * @return response data
     */
    public List<LocationData> getLocation(String latitude, String longitude) {
        String lattlong = String.format("%s,%s", latitude, longitude);

        return restTemplate.exchange(endpointConfig.getWheatherLocationEndpoint(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<LocationData>>() {}, lattlong).getBody();
    }

    /**
     * Retrieves the wheather data
     *
     * @param woeid where on Earth id
     * @return response data
     */
    public WheatherWrapper getWheatherData(Integer woeid) {
        return restTemplate.getForObject(endpointConfig.getWheatherTemperatureEndpoint(), WheatherWrapper.class, woeid);
    }
}
