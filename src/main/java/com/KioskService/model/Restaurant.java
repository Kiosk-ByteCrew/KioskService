package com.KioskService.model;


import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "tenantId")
    private int tenantId;
}
