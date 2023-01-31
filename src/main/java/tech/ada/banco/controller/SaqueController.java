package tech.ada.banco.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.banco.services.Saque;

import java.math.BigDecimal;

@RestController
@RequestMapping("/saque")
public class SaqueController {

    private final Saque saque;

    public SaqueController(Saque saque) {
        this.saque = saque;
    }

    @PostMapping("{conta}")
    public BigDecimal createSaque(@PathVariable int conta, @RequestParam BigDecimal valor) {
        return saque.executar(conta, valor);
    }
}
