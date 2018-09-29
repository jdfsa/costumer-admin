package br.com.jdfs.customer.customeradmin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Geolocalization response data
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoData {
    public String ipv4;

    @JsonProperty("continent_name")
    public String continentName;

    @JsonProperty("country_name")
    public String countryName;

    @JsonProperty("city_name")
    public String cityName;

    public String latitude;

    public String longitude;
}
