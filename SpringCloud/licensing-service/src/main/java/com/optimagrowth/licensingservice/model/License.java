package com.optimagrowth.licensingservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * POJO(Plain Old Java Object)
 */
@Getter @Setter @ToString 
public class License { 
    public License() {

    }
    private int id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}
