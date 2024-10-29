package com.example.firstproject.dto;


import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CoffeeDto {
    private Long id;
    private String name;
    private Integer price;

    public Coffee toEntitiy() {
        return new Coffee(id, name, price);
    }
}
