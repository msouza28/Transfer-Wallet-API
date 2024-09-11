package com.github.msouza28.desafio_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CarteriaNotFoundException extends GlobalException{

    private Long carteiraId;

    public CarteriaNotFoundException(Long carteiraId) {
        this.carteiraId = carteiraId;
    }

    @Override
    public ProblemDetail toProblemDetail() {
       var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

       pb.setTitle("Carteira não encontrada");
       pb.setDetail("Não a carteira com id " + carteiraId);

       return pb;
    }
}
