package com.github.msouza28.desafio_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "carteira_sender_id")
    private Carteira sender;
    @ManyToOne
    @JoinColumn(name = "carteira_receiver_id")
    private Carteira receiver;
    @Column(name = "value")
    private BigDecimal value;

    public Transferencia() {
    }

    public Transferencia(Carteira sender, Carteira receiver, BigDecimal value) {
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Carteira getSender() {
        return sender;
    }

    public void setSender(Carteira sender) {
        this.sender = sender;
    }

    public Carteira getReceiver() {
        return receiver;
    }

    public void setReceiver(Carteira receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
