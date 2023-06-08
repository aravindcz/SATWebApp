package com.aravindcz.SATWebApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@ConfigurationProperties("satwebapp.menu")
@Component
@Getter
@Setter
@NoArgsConstructor
public class MenuDTO implements Serializable {

    private List<String> options;

}
