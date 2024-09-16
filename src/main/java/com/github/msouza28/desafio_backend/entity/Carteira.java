package com.github.msouza28.desafio_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_carteira")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "cpf_cnpj",unique = true)
    private String cpfCnpj;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "saldo")
    private BigDecimal saldo = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "tipo_carteira_id")
    private TipoCarteira tipoCarteira;

    public Carteira(String nomeCompleto, String cpfCnpj, String email, String senha, TipoCarteira tipoCarteira) {
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.senha = senha;
        this.tipoCarteira = tipoCarteira;
    }

    public boolean isTransferAllowedForWalletType() {
        return this.tipoCarteira.equals(TipoCarteira.TipoCarteiraEnum.USUARIO.get());
    }

    public boolean isBalanceEqualOrGreaterThan(@DecimalMin("0.01") @NotNull BigDecimal value) {
        return this.saldo.doubleValue() >= value.doubleValue();
    }

    public void envia(@DecimalMin("0.01") @NotNull BigDecimal value) {
        this.saldo = this.saldo.subtract(value);
    }

    public void recebe(@DecimalMin("0.01") @NotNull BigDecimal value) {
        this.saldo = this.saldo.add(value);
    }
}
