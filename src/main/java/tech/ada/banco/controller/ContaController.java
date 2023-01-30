package tech.ada.banco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.banco.model.Conta;
import tech.ada.banco.model.ModalidadeConta;
import tech.ada.banco.model.Pessoa;

import java.util.List;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {

    @GetMapping("{conta}")
    public Conta getConta(@PathVariable int conta) {
        return Conta.obtemContaPeloNumero(conta);
    }

    @GetMapping
    public List<Conta> getContas() {
        return Conta.getContas();
    }

    @PostMapping
    public Conta createConta(@RequestParam ModalidadeConta modalidade,
                                @RequestBody(required = false) Pessoa pessoa) {
        return new Conta(modalidade, pessoa);
    }
}
