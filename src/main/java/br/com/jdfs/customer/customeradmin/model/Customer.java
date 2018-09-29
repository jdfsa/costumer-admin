package br.com.jdfs.customer.customeradmin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Customer entity
 */
@Data
public class Customer {

    @Id
    public String id;

    public String name;

    public Integer age;

    @JsonProperty("min_temperature")
    public Float minTemp;

    @JsonProperty("max_temperature")
    public Float maxTemp;
}
