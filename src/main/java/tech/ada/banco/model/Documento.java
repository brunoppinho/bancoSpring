package tech.ada.banco.model;

public abstract class Documento {

    private String valor;

    protected Documento(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
