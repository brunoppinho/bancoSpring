package tech.ada.banco.services;

import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

public class Deposito {

    public BigDecimal executar(int numeroConta, BigDecimal valor) {
        Conta conta = Conta.obtemContaPeloNumero(numeroConta);

        if (conta != null) {
            // Etapa 4
            conta.deposito(valor);
            System.out.println("O saldo da conta é de: R$" + conta.getSaldo());
        } else {
            System.out.println("Conta não pode ser nula. Tente novamente.");
        }
        return conta.getSaldo();
    }
}
