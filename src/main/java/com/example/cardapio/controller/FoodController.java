package com.example.cardapio.controller;

import com.example.cardapio.model.Food;
import com.example.cardapio.repository.FoodRepository;
import com.example.cardapio.service.FoodService;
import dto.FoodRequestDTO;
import dto.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> getAll() {
        List<FoodResponseDTO> foods = foodService.findAll()
                .stream()
                .map(FoodResponseDTO::new)
                .toList();
        return ResponseEntity.ok(foods);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> getById(@PathVariable Long id) {
        return foodService.procurarId(id)
                .map(food -> ResponseEntity.ok(new FoodResponseDTO(food)))
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<FoodResponseDTO> saveFood(@RequestBody FoodRequestDTO data) {
        Food savedFood = foodService.saveFood(new Food(data));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new FoodResponseDTO(savedFood));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletFood(@PathVariable Long id) {
        boolean deleted = foodService.deletar(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
