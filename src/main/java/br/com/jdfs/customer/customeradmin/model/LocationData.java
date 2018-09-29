package br.com.jdfs.customer.customeradmin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Wheather data
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationData {

    public Integer woeid;

    @JsonProperty("latt_long")
    public String lattLong;
}
