package br.com.jdfs.customer.customeradmin.service;

import br.com.jdfs.customer.customeradmin.config.EndpointConfig;
import br.com.jdfs.customer.customeradmin.model.GeoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Geolocalization service
 */
@Service
public class GeolocalizationService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EndpointConfig endpointConfig;

    /**
     * Retrieves the localization data
     *a
     * @param ipAddress user ip address
     * @return localization data
     */
    public GeoWrapper getData(String ipAddress) {
        return restTemplate.getForObject(endpointConfig.getGeolocalizationEndpoint(), GeoWrapper.class, ipAddress);
    }

}
