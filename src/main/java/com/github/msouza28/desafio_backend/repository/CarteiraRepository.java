package com.github.msouza28.desafio_backend.repository;

import com.github.msouza28.desafio_backend.entity.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Optional<Carteira> findByCpfCnpjOrEmail(String s, String email);
}
