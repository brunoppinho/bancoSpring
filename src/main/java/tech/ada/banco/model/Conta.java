package tech.ada.banco.model;

import tech.ada.banco.exceptions.SaldoInsuficienteException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Conta {

    private static Map<Integer, Conta> contas = new HashMap<>();
    private final ModalidadeConta tipo;
    protected BigDecimal saldo;
    private int numeroConta;
    private final String agencia;
    private final Pessoa titular;

    public Conta(ModalidadeConta tipo, Pessoa titular) {
        this.tipo = tipo;
        this.titular = titular;
        agencia = "0001";
        saldo = BigDecimal.ZERO;
        escolheNumeroConta();
        contas.put(numeroConta, this);
    }

    public static Conta obtemContaPeloNumero(int numeroConta) {
        return contas.get(numeroConta);
    }

    private void escolheNumeroConta() {
        numeroConta = new Random().nextInt(99999);
    }

    public ModalidadeConta getTipo() {
        return tipo;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void deposito(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Valor menor que zero!");
        }

        saldo = saldo.add(valor);
    }

    public void saque(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Valor menor que zero!");
        }

        if (valor.compareTo(saldo) > 0) {
            throw new SaldoInsuficienteException();
        } else {
            saldo = saldo.subtract(valor);
        }
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public Pessoa getTitular() {
        return titular;
    }

    public static List<Conta> getContas() {
        return new ArrayList<>(contas.values());
    }

}
