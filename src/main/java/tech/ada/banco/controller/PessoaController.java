package tech.ada.banco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.banco.exceptions.ResourceNotFoundException;
import tech.ada.banco.model.Pessoa;
import tech.ada.banco.repository.PessoaRepository;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaRepository repository;

    @GetMapping("{id}")
    public Pessoa getPessoa(@PathVariable int id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping
    public List<Pessoa> getPessoas() {
        return repository.findAll();
    }

    @PostMapping
    public Pessoa createPessoa(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @PutMapping
    public Pessoa atualizaPessoa(@RequestBody Pessoa pessoa) {
        return repository.save(pessoa);
    }
}
