package com.github.msouza28.desafio_backend.service;

import com.github.msouza28.desafio_backend.client.NotificationClient;
import com.github.msouza28.desafio_backend.entity.Transferencia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transferencia transferencia){
        try {
            logger.info("Enviando notificação");
           var resp = notificationClient.sendNotification(transferencia);
           if(resp.getStatusCode().isError()){
               logger.error("Erro ao enviar notificação, status code não é OK");
           }
        }catch (Exception e){
            logger.error("Erro ao enviar notificação", e);
        }

    }
}
