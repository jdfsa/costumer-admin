package br.com.jdfs.customer.customeradmin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Wheather detailed data
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WheatherData {

    @JsonProperty("min_temp")
    public Float minTemp;

    @JsonProperty("max_temp")
    public Float maxTemp;

    @JsonProperty("the_temp")
    public Float theTemp;
}
