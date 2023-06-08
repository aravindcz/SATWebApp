package com.aravindcz.SATWebApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SATResult implements Serializable {


    @Id
    private String name;

    private String address;

    private String city;

    private String country;

    private String pincode;

    private int score;

    private boolean isPassed;



}
