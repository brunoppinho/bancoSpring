package tech.ada.banco.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

@Service
@Slf4j
public final class Saque {

    public BigDecimal executar(int numeroConta, BigDecimal valor) {
        Conta conta = Conta.obtemContaPeloNumero(numeroConta);

        conta.saque(valor);
        log.info("O saldo resultante após o saque é de R$ {}", conta.getSaldo());
        return conta.getSaldo();
    }
}
