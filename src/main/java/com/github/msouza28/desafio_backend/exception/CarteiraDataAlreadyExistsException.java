package com.github.msouza28.desafio_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CarteiraDataAlreadyExistsException extends GlobalException {

    private String detail;


    public CarteiraDataAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb =  ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Dados já existem para essa carteira");
        pb.setDetail(detail);
        return pb;
    }
}
