package br.com.jdfs.customer.customeradmin.model;

import lombok.Data;

/**
 * Geolocalization response wrapper
 */
@Data
public class GeoWrapper {
    public String status;
    public GeoData data;
}
