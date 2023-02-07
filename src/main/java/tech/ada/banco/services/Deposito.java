package tech.ada.banco.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.ada.banco.exceptions.ResourceNotFoundException;
import tech.ada.banco.model.Conta;
import tech.ada.banco.repository.ContaRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class Deposito {

    private final ContaRepository repository;

    public Deposito(ContaRepository repository) {
        this.repository = repository;
    }

    public BigDecimal executar(int numeroConta, BigDecimal valor) {
        valor = valor.setScale(2, RoundingMode.HALF_UP);

        Conta conta = repository.findContaByNumeroConta(numeroConta).orElseThrow(ResourceNotFoundException::new);
        conta.deposito(valor);
        log.info("O saldo da conta é de: R$ {}", conta.getSaldo());
        repository.save(conta);
        return conta.getSaldo();
    }
}
