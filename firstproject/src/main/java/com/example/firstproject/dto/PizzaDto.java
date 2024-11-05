package com.example.firstproject.dto;

import com.example.firstproject.entity.Pizza;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class PizzaDto {
    Long id;
    String name;
    Integer price;

    public static PizzaDto createPizzaDto(Pizza pizza){
        return new PizzaDto(pizza.getId(), pizza.getName(), pizza.getPrice());
    }
}
