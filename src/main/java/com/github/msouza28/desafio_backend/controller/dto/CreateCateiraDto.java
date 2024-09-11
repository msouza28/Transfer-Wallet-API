package com.github.msouza28.desafio_backend.controller.dto;

import com.github.msouza28.desafio_backend.entity.Carteira;
import com.github.msouza28.desafio_backend.entity.TipoCarteira;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCateiraDto(@NotBlank String nomeCompleto,
                               @NotBlank String cpfCnpj,
                               @NotBlank String email,
                               @NotBlank String senha,
                               @NotNull TipoCarteira.TipoCarteiraEnum tipoCarteira) {

    public Carteira toCarteira(){
        return new Carteira(nomeCompleto,cpfCnpj,email,senha,tipoCarteira.get());
    }
}
