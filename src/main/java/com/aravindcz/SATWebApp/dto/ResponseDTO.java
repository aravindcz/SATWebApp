package com.aravindcz.SATWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO implements Serializable {

    private String status;
    private int code;
    private String message;
    private Object data;

}
