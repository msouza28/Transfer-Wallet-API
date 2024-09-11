package com.github.msouza28.desafio_backend.repository;

import com.github.msouza28.desafio_backend.entity.TipoCarteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCarteiraRespository extends JpaRepository<TipoCarteira, Long> {
}
