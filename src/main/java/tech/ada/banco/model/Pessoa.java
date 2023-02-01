package tech.ada.banco.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tech.ada.banco.exceptions.ValorInvalidoException;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @SequenceGenerator(name = "pessoaSequenceGenerator", sequenceName = "PESSOA_SQ", initialValue = 100)
    @GeneratedValue(generator = "pessoaSequenceGenerator", strategy = GenerationType.SEQUENCE)
    @Column(updatable = false)
    private Long id;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "NOME")
    private String nome;

    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        setDataNascimento(dataNascimento);
        this.cpf = cpf;
        this.nome = nome;
    }

    protected Pessoa() {

    }

    public String toString() {
        return "Nome: " + nome + " telefone: " + telefone + " e cpf: " + cpf;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento.plusYears(18).isAfter(LocalDate.now())) {
            throw new ValorInvalidoException();
        } else {
            this.dataNascimento = dataNascimento;
        }
    }

}
