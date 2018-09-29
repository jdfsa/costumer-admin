package br.com.jdfs.customer.customeradmin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Wrapper for wheather data
 */
@Data
public class WheatherWrapper {

    @JsonProperty("consolidated_weather")
    public List<WheatherData> consolidatedWheater;
}
