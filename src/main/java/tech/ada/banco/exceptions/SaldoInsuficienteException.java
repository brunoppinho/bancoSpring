package tech.ada.banco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Limite acima do saldo disponível!")
public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException() {
        super("Limite acima do saldo disponível!");
    }
}
