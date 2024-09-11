package com.github.msouza28.desafio_backend.service;

import com.github.msouza28.desafio_backend.client.AuthorizationClient;
import com.github.msouza28.desafio_backend.controller.dto.TransferDto;
import com.github.msouza28.desafio_backend.exception.GlobalException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(@Valid TransferDto transferencia){
        var resp = authorizationClient.isAuthorized();

        if(resp.getStatusCode().isError()){
            throw new GlobalException();
        }

        return resp.getBody().authorized();
    }
}
