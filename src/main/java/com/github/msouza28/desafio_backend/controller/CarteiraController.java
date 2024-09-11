package com.github.msouza28.desafio_backend.controller;

import com.github.msouza28.desafio_backend.controller.dto.CreateCateiraDto;
import com.github.msouza28.desafio_backend.entity.Carteira;
import com.github.msouza28.desafio_backend.service.CarteiraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping("/carteiras")
    public ResponseEntity<Carteira> create(@RequestBody @Valid CreateCateiraDto dto){
        var carteira = carteiraService.createCarteira(dto);
        return ResponseEntity.ok(carteira);
    }
}
