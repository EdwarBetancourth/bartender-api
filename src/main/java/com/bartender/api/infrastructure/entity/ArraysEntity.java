package com.bartender.api.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "arrays")
public class ArraysEntity {

    @NotNull
    @Id
    private int id;

    @NotNull
    @Column(name = "input_array")
    private String inputArray;

}
