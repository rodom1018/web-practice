package com.example.firstproject.controller;

import com.example.firstproject.dto.PizzaDto;
import com.example.firstproject.entity.Pizza;
import com.example.firstproject.service.PizzaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    //1. 피자 조회(목록 조회)
    @GetMapping("/api/pizzas")
    public ResponseEntity<List<PizzaDto>> getPizzas(){
        List<PizzaDto> pizzas = pizzaService.getPizzas();
        return ResponseEntity.status(HttpStatus.OK).body(pizzas);
    }

    //2. 피자 조회(단건 조회)
    @GetMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> getPizza(@PathVariable Long id){
        PizzaDto pizza = pizzaService.getPizza(id);
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    //3. 피자 메뉴를 생성
    @PostMapping("/api/pizza")
    public ResponseEntity<PizzaDto> createPizza(@RequestBody PizzaDto dto){
        PizzaDto pizza = pizzaService.createPizza(dto);
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }

    //4. 피자 메뉴를 수정
    @PatchMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> updatePizza(@PathVariable Long id, @RequestBody PizzaDto dto){
        PizzaDto pizza = pizzaService.updatePizza(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(pizza);
    }


    //5. 피자 삭제
    @DeleteMapping("/api/pizza/{id}")
    public ResponseEntity<PizzaDto> deletePizza(@PathVariable Long id){
        PizzaDto pizza = pizzaService.deletePizza(id);
        return ResponseEntity.status(HttpStatus.OK).body(pizza);

    }
}
