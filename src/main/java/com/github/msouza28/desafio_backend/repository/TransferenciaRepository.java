package com.github.msouza28.desafio_backend.repository;

import com.github.msouza28.desafio_backend.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferenciaRepository extends JpaRepository<Transferencia, UUID> {
}
