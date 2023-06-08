package com.aravindcz.SATWebApp.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class SATResultDTO implements Serializable {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String name;


    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 -]+$")
    private String address;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String city;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]+$")
    private String country;

    @NotBlank
    @Pattern(regexp = "^[0-9]+$")
    @Size(min = 5,max = 10)
    private String pincode;

    @Min(value = 400)
    @Max(value = 1600)
    private int score;


}
