package com.github.msouza28.desafio_backend.service;

import com.github.msouza28.desafio_backend.controller.dto.CreateCateiraDto;
import com.github.msouza28.desafio_backend.entity.Carteira;
import com.github.msouza28.desafio_backend.exception.CarteiraDataAlreadyExistsException;
import com.github.msouza28.desafio_backend.repository.CarteiraRepository;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {

    private final CarteiraRepository carteiraRepository;

    public CarteiraService(CarteiraRepository carteiraRepository) {
        this.carteiraRepository = carteiraRepository;
    }

    public Carteira createCarteira(CreateCateiraDto dto) {
        if(carteiraRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email()).isPresent()){
            throw new CarteiraDataAlreadyExistsException("CPF-CNPJ ou Email já existe");
        }

        var carteira = carteiraRepository.save(dto.toCarteira());
        return  carteira;
    }
}
