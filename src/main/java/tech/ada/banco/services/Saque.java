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
public final class Saque {

    private final ContaRepository repository;

    public Saque(ContaRepository repository) {
        this.repository = repository;
    }

    public BigDecimal executar(int numeroConta, BigDecimal valor) {
        valor = valor.setScale(2, RoundingMode.HALF_UP);

        Conta conta = repository.findContaByNumeroConta(numeroConta).orElseThrow(ResourceNotFoundException::new);

        conta.saque(valor);
        repository.save(conta);
        log.info("O saldo resultante após o saque é de R$ {}", conta.getSaldo());
        return conta.getSaldo();
    }
}
