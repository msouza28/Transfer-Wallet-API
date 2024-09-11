package com.github.msouza28.desafio_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends GlobalException{
    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Transferencia não autorizada");
        pb.setDetail("O serviço de autorização não permitiu essa transferencia");
        return pb;
    }
}
