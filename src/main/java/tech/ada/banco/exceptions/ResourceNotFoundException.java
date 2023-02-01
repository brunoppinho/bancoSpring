package tech.ada.banco.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Recurso não encontrado.")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Limite acima do saldo disponível!");
    }
}
