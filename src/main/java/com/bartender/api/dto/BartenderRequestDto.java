package com.bartender.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BartenderRequestDto {

    @JsonProperty("n_iteration")
    @NotNull(message="n_iteration is required.")
    private Integer NIteration;

    @JsonProperty("id_pila")
    @NotNull(message="id_pila is required.")
    @Range( min = 1, max = 5, message = "id_pila must be between 1 and 5.")
    private Integer PilaId;

}
