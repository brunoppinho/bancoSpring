package tech.ada.banco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Valor informado está inválido.")
public class ValorInvalidoException extends RuntimeException {

    public ValorInvalidoException() {
        super("Valor informado está inválido.");
    }
}
