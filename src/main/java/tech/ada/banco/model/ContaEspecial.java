package tech.ada.banco.model;

import tech.ada.banco.exceptions.SaldoInsuficienteException;

import java.math.BigDecimal;

public class ContaEspecial extends Conta {

    private BigDecimal limiteEspecial;

    public ContaEspecial(ModalidadeConta tipo, Pessoa titular) {
        super(tipo, titular);
        this.limiteEspecial = BigDecimal.valueOf(100);
    }

    public BigDecimal getLimiteEspecial() {
        return limiteEspecial;
    }

    public void setLimiteEspecial(BigDecimal limiteEspecial) {
        this.limiteEspecial = limiteEspecial;
    }

    @Override
    public void saque(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Valor menor que zero!");
        }
        if (valor.compareTo(saldo.add(limiteEspecial)) > 0) {
            throw new SaldoInsuficienteException();
        } else {
            saldo = saldo.subtract(valor);

            BigDecimal bonus = valor.multiply(BigDecimal.valueOf(0.1));

            limiteEspecial = limiteEspecial.subtract(bonus);

        }
    }

    @Override
    public void deposito(BigDecimal valor) {
        super.deposito(valor);

        BigDecimal bonus = valor.multiply(BigDecimal.valueOf(0.1));

        limiteEspecial = limiteEspecial.add(bonus);
    }
}
