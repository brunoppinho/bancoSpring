package tech.ada.banco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.banco.services.Saque;

import java.math.BigDecimal;

@RestController
@RequestMapping("/saque")
@RequiredArgsConstructor
public class SaqueController {

    private final Saque saque;

    @PostMapping("{conta}")
    public BigDecimal createSaque(@PathVariable int conta, @RequestParam BigDecimal valor) {
        return saque.executar(conta, valor);
    }
}
