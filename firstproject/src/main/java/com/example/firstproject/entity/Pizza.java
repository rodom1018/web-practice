package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.WebProperties;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Integer price;


    public static Pizza createPizza(PizzaDto dto) {
        return new Pizza(dto.getId(), dto.getName(), dto.getPrice());
    }

    public void patch(PizzaDto dto) {
        if(dto.getName() != null){
            this.name = dto.getName();
        }

        if(dto.getPrice() != null){
            this.price = dto.getPrice();
        }
    }
}
