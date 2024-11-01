package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import com.example.firstproject.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoffeeApiController {

    @Autowired
    CoffeeService coffeeService;

    //index, show
    @GetMapping("/api/coffees")
    public List<Coffee> index(){
        return coffeeService.index();
    }

    @GetMapping("/api/coffee/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeService.show(id);
    }

    @PostMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto){
        Coffee updated = coffeeService.update(id, dto);

        return (updated != null)?
            ResponseEntity.status(HttpStatus.OK).body(updated):
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null)?
                ResponseEntity.status(HttpStatus.OK).body(deleted):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
