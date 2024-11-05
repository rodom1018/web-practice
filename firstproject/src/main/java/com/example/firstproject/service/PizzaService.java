package com.example.firstproject.service;


import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.repository.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    public List<PizzaDto> getPizzas() {
        // java.util.ArrayList
        // System.out.println(pizzaRepository.findAll().getClass().getName());
        return pizzaRepository.findAll()
                .stream()
                .map(pizza -> PizzaDto.createPizzaDto(pizza))
                .collect(Collectors.toList());
    }

    public PizzaDto getPizza(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        return PizzaDto.createPizzaDto(pizza);
    }

    @Transactional
    public PizzaDto createPizza(PizzaDto dto) {
        Pizza newpizza = Pizza.createPizza(dto);
        pizzaRepository.save(newpizza);
        return dto;
    }

    @Transactional
    public PizzaDto updatePizza(Long id, PizzaDto dto) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("수정 오류, 없는 피자 입니다"));

        if(pizza.getId() != dto.getId()){
            throw new IllegalArgumentException("수정 오류, 인자가 잘못 되었습니다");
        }

        pizza.patch(dto);

        return PizzaDto.createPizzaDto(pizza);

    }

    @Transactional
    public PizzaDto deletePizza(Long id) {
        Pizza pizza = pizzaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("삭제 오류, 없는 피자 입니다"));

        pizzaRepository.delete(pizza);

        return PizzaDto.createPizzaDto(pizza);

    }
}
