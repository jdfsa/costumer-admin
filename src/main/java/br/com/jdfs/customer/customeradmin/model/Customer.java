package br.com.jdfs.customer.customeradmin.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Customer model
 */
@Data
public class Customer {

    @Id
    public String id;

    public String name;
    public Integer age;

}
