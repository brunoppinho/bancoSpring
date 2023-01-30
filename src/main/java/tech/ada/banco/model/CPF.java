package tech.ada.banco.model;

public class CPF extends Documento {

    private String cor;

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public CPF(String valor) {
        super(valor);
    }

    @Override
    public void setValor(String valor) {
        if (valor.length() != 11) {
            throw new RuntimeException("Tamanho do CPF inválido.");
        }
        super.setValor(valor);
    }
}
