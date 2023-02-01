package tech.ada.banco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.banco.services.Pix;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pix")
@RequiredArgsConstructor
public class PixController {

    private final Pix pix;

    @PostMapping("{conta}")
    public BigDecimal createPixTransaction(@PathVariable int conta, @RequestParam int destino,
                                           @RequestParam BigDecimal valor) {
        return pix.executar(conta, destino, valor);
    }
}
