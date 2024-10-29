package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CoffeeService {

    @Autowired
    CoffeeRepository coffeeRepository;

    //index, show
    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }


    public Coffee show(@PathVariable Long id){
        return coffeeRepository.findById(id).orElse(null);
    }


    public Coffee update(@PathVariable Long id, @RequestBody CoffeeDto dto){
        Coffee coffee = dto.toEntitiy();
        Coffee target = coffeeRepository.findById(id).orElse(null);

        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }


    public Coffee delete(@PathVariable Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        coffeeRepository.delete(coffee);
        return coffee;
    }
}
