package com.aravindcz.SATWebApp.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public
class ScoreDTO implements Serializable {

    @Min(value = 400)
    @Max(value = 1600)
    private int score;
}
