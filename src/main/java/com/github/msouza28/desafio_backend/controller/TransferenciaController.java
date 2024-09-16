package com.github.msouza28.desafio_backend.controller;

import com.github.msouza28.desafio_backend.controller.dto.TransferDto;
import com.github.msouza28.desafio_backend.entity.Transferencia;
import com.github.msouza28.desafio_backend.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transferencia> transfer(@RequestBody @Valid TransferDto dto){
        var resp = transferenciaService.transfer(dto);
        return ResponseEntity.ok(resp);
    }
}
