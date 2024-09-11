package com.github.msouza28.desafio_backend.service;

import com.github.msouza28.desafio_backend.controller.dto.TransferDto;
import com.github.msouza28.desafio_backend.entity.Carteira;
import com.github.msouza28.desafio_backend.entity.Transferencia;
import com.github.msouza28.desafio_backend.exception.CarteriaNotFoundException;
import com.github.msouza28.desafio_backend.exception.InsufficientBalanceException;
import com.github.msouza28.desafio_backend.exception.TransferNotAllowedForWalletTypeException;
import com.github.msouza28.desafio_backend.exception.TransferNotAuthorizedException;
import com.github.msouza28.desafio_backend.repository.CarteiraRepository;
import com.github.msouza28.desafio_backend.repository.TransferenciaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferenciaService {

    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferenciaRepository transferenciaRepository;
    private final CarteiraRepository carteiraRepository;

    public TransferenciaService(AuthorizationService authorizationService,
                                NotificationService notificationService,
                                TransferenciaRepository transferenciaRepository, CarteiraRepository carteiraRepository) {
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.transferenciaRepository = transferenciaRepository;
        this.carteiraRepository = carteiraRepository;
    }

    @Transactional
    public Transferencia transfer(@Valid TransferDto transferDto) {

        var sender = carteiraRepository.findById(transferDto.payer()).orElseThrow(() -> new CarteriaNotFoundException(transferDto.payer()));
        var receiver = carteiraRepository.findById(transferDto.payee()).orElseThrow(() -> new CarteriaNotFoundException(transferDto.payee()));

        validaTransfer(transferDto, sender);

        sender.envia(transferDto.value());
        receiver.recebe(transferDto.value());

        var transferencia = new Transferencia(sender, receiver, transferDto.value());


        carteiraRepository.save(sender);
        carteiraRepository.save(receiver);
        var transferResult = transferenciaRepository.save(transferencia);

        //processo assincrono para envio das notificações, sem causar qualquer exceção caso de algum erro.
        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validaTransfer(@Valid TransferDto transferDto, Carteira sender) {

        if (!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if(!sender.isBalanceEqualOrGreaterThan(transferDto.value())){
            throw new InsufficientBalanceException();
        }

        if(!authorizationService.isAuthorized(transferDto)){
            throw new TransferNotAuthorizedException();
        }
    }
}
