package br.com.jdfs.customer.customeradmin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("api")
public class EndpointConfig {
    public String geolocalizationEndpoint;
    public String wheatherLocationEndpoint;
    public String wheatherTemperatureEndpoint;
    public String ipDiscoveryEndpoint;
}
