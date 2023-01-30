package tech.ada.banco.model;

import java.time.LocalDate;

public class Pessoa {

    private LocalDate dataNascimento;
    private Documento documento;
    private String telefone;
    private String nome;

    public Pessoa(String nome, Documento documento, LocalDate dataNascimento) {
        setDataNascimento(dataNascimento);
        this.documento = documento;
        this.nome = nome;
    }

    public Pessoa(String nome, Documento documento, String dataNascimento) {
        this(nome, documento, LocalDate.parse(dataNascimento));
    }

    public String toString() {
        return "Nome: " + nome + " telefone: " + telefone + " e documento: " + documento.getValor();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento.plusYears(18).isAfter(LocalDate.now())) {
            throw new RuntimeException("Data inválida!");
        } else {
            this.dataNascimento = dataNascimento;
        }
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
