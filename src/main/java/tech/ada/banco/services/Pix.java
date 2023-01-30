package tech.ada.banco.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

@Service
@Slf4j
public class Pix {

    public BigDecimal executar(int contaOrigem, int contaDestino, BigDecimal valor) {
        Conta origem = Conta.obtemContaPeloNumero(contaOrigem);
        Conta destino = Conta.obtemContaPeloNumero(contaDestino);

        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Operação não foi realizada pois o valor da transação é negativo.");
        }

        origem.saque(valor);
        destino.deposito(valor);
        log.info("Operação realizada com sucesso.");
        return origem.getSaldo();
    }

}
