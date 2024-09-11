package com.github.msouza28.desafio_backend.config;

import com.github.msouza28.desafio_backend.entity.TipoCarteira;
import com.github.msouza28.desafio_backend.repository.TipoCarteiraRespository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final TipoCarteiraRespository tipoCarteiraRespository;

    public DataLoader(TipoCarteiraRespository tipoCarteiraRespository) {
        this.tipoCarteiraRespository = tipoCarteiraRespository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(TipoCarteira.TipoCarteiraEnum.values())
                .forEach(tipoCarteira -> tipoCarteiraRespository.save(tipoCarteira.get()));
    }
}
