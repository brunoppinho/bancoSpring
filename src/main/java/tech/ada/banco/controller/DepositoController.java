package tech.ada.banco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.banco.services.Deposito;

import java.math.BigDecimal;

@RestController
@RequestMapping("/deposito")
@RequiredArgsConstructor
public class DepositoController {

    private final Deposito deposito;

    @PostMapping("{conta}")
    public BigDecimal deposito(@PathVariable int conta, @RequestParam BigDecimal valor) {
        return deposito.executar(conta, valor);
    }
}
