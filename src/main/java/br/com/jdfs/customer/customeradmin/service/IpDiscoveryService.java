package br.com.jdfs.customer.customeradmin.service;

import br.com.jdfs.customer.customeradmin.config.EndpointConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * IP address discovery service
 */
@Service
public class IpDiscoveryService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EndpointConfig endpointConfig;

    /**
     * Gets the IP address using a remote service
     * @return
     */
    public String getIpAddress() {
        return restTemplate.getForObject(endpointConfig.getIpDiscoveryEndpoint(), String.class);
    }
}
