package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Integer price;

    public void patch(Coffee coffee) {
        if(coffee.id != null){
            this.id = coffee.id;
        }
        if(coffee.name != null){
            this.name = coffee.name;
        }
        if(coffee.price != null) {
            this.price = coffee.price;
        }
    }
}
