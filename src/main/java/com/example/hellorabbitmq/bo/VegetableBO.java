package com.example.hellorabbitmq.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class VegetableBO implements Serializable {

    private String name;

    private String color;

    private Double value;

}
