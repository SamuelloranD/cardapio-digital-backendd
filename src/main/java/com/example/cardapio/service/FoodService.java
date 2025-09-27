package com.example.cardapio.service;

import com.example.cardapio.model.Food;
import com.example.cardapio.repository.FoodRepository;
import dto.FoodRequestDTO;
import dto.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;

    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    public Food saveFood(Food foodData) {
        return foodRepository.save(foodData);
    }

    public boolean deletar(Long id) {
        Optional<Food> food = foodRepository.findById(id);
        if (food.isPresent()) {
            foodRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Food> procurarId(Long id) {
        return foodRepository.findById(id);
    }
}
